package com.ticketexchange.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.auth.CurrentUser;
import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.domain.EarnedProduct;
import com.ticketexchange.domain.Member;
import com.ticketexchange.domain.Product;
import com.ticketexchange.domain.Ticket;
import com.ticketexchange.repository.EarnedProductRepository;
import com.ticketexchange.repository.MemberRepository;
import com.ticketexchange.repository.ProductRepository;
import com.ticketexchange.repository.TicketRepository;
import com.ticketexchange.service.dto.ApplyProductDto;
import com.ticketexchange.service.dto.CreateProductDto;
import com.ticketexchange.service.dto.ProductDto;

@Service
@Transactional
public class ProductService {
	private final MemberRepository memberRepository;
	private final TicketRepository ticketRepository;
	private final ProductRepository productRepository;
	private final EarnedProductRepository earnedProductRepository;

	public ProductService(MemberRepository memberRepository, TicketRepository ticketRepository,
		ProductRepository productRepository, EarnedProductRepository earnedProductRepository) {
		this.memberRepository = memberRepository;
		this.ticketRepository = ticketRepository;
		this.productRepository = productRepository;
		this.earnedProductRepository = earnedProductRepository;
	}

	public ProductDto registerProduct(CreateProductDto createProductDto) {
		Product product = productRepository.save(createProductDto.toEntity());
		return ProductDto.of(product);
	}

	public ApplyProductDto applyProduct(MemberToken token, Long productId, LocalDate now) {
		Product product = productRepository.findByIdWithPessimisticLock(productId)
			.orElseThrow(IllegalArgumentException::new);
		Long memberId = token.getId();
		int neededTicketCount = product.getNeedTicketCount();
		if (!hasEnoughTicket(neededTicketCount, memberId, now) || notEnoughProduct(product.getRemainQuantity())
			|| notWinningProduct(product.getProbability())) {
			return new ApplyProductDto(false);
		}
		useTicketForProduct(memberId, now, neededTicketCount, product.getName());
		product.decreaseRemainQuantity();
		memberEarnedProduct(memberId, product);
		return new ApplyProductDto(true);
	}

	private boolean notWinningProduct(double productProbability) {
		return productProbability >= ThreadLocalRandom.current().nextDouble();
	}

	private boolean notEnoughProduct(int remainQuantity) {
		return remainQuantity <= 0;
	}

	private void useTicketForProduct(Long memberId, LocalDate now, int neededTicketCount, String productName) {
		List<Ticket> tickets = ticketRepository.findAllByMemberIdAndExpireDateBeforeAndUsed(memberId, now,
			false, sortAcquiredDateAsc()).subList(0, neededTicketCount);
		tickets.forEach(t -> t.use(productName));
	}

	private void memberEarnedProduct(Long memberId, Product product) {
		Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
		earnedProductRepository.save(new EarnedProduct(member, product));
	}

	private Sort sortAcquiredDateAsc() {
		return Sort.by("acquireDate").ascending();
	}

	private boolean hasEnoughTicket(int neededTicketCount, Long memberId, LocalDate now) {
		return neededTicketCount <= ticketRepository.countByMemberIdAndExpireDateBefore(memberId, now);
	}

	@Transactional(readOnly = true)
	public List<ProductDto> findAllValidProducts(LocalDate now) {
		List<Product> products = productRepository.findAllByValidStartDateAfterAndValidEndDateBefore(
			now, now);
		return products.stream().map(ProductDto::of).toList();
	}
}
