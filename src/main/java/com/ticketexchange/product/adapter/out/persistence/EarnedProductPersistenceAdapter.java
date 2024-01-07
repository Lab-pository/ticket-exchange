package com.ticketexchange.product.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticketexchange.product.application.port.out.EarnedProductPort;
import com.ticketexchange.product.application.port.out.EarnedProductQueryPort;
import com.ticketexchange.product.domain.EarnedProduct;

@Repository
public class EarnedProductPersistenceAdapter implements EarnedProductPort, EarnedProductQueryPort {

    private final EarnedProductRepository earnedProductRepository;

    public EarnedProductPersistenceAdapter(final EarnedProductRepository earnedProductRepository) {
        this.earnedProductRepository = earnedProductRepository;
    }

    @Override
    public EarnedProduct save(final EarnedProduct earnedProduct) {
        return EarnedProductMapper.toDomain(
                earnedProductRepository.save(EarnedProductMapper.toJpaEntity(earnedProduct)));
    }

    @Override
    public List<EarnedProduct> findAllByMemberId(final Long memberId) {
        return earnedProductRepository.findAllByMemberId(memberId)
                .stream()
                .map(EarnedProductMapper::toDomain)
                .toList();
    }
}
