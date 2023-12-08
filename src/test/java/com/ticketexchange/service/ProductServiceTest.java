package com.ticketexchange.service;

import static com.ticketexchange.fixture.MemberFixture.MEMBER_ID;
import static com.ticketexchange.fixture.MemberFixture.회원;
import static com.ticketexchange.fixture.MemberFixture.회원토큰;
import static com.ticketexchange.fixture.ProductFixture.END_DATE;
import static com.ticketexchange.fixture.ProductFixture.NEED_TICKET_COUNT;
import static com.ticketexchange.fixture.ProductFixture.PROBABILITY;
import static com.ticketexchange.fixture.ProductFixture.PRODUCT_ID;
import static com.ticketexchange.fixture.ProductFixture.PRODUCT_NAME;
import static com.ticketexchange.fixture.ProductFixture.START_DATE;
import static com.ticketexchange.fixture.ProductFixture.당첨되는_상품;
import static com.ticketexchange.fixture.ProductFixture.당첨되지_않는_상품;
import static com.ticketexchange.fixture.ProductFixture.상품;
import static com.ticketexchange.fixture.ProductFixture.상품_생성_DTO;
import static com.ticketexchange.fixture.TicketFixture.티켓;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.repository.EarnedProductRepository;
import com.ticketexchange.repository.MemberRepository;
import com.ticketexchange.repository.ProductRepository;
import com.ticketexchange.repository.TicketRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ProductService.class)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    TicketRepository ticketRepository;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    EarnedProductRepository earnedProductRepository;

    @Test
    void 상품_등록_테스트() {
        when(productRepository.save(any())).thenReturn(상품());

        final var result = productService.registerProduct(상품_생성_DTO());

        assertAll(() -> {
            assertThat(result.getProductId()).isEqualTo(PRODUCT_ID);
            assertThat(result.getName()).isEqualTo(PRODUCT_NAME);
            assertThat(result.getProbability()).isEqualTo(PROBABILITY);
            assertThat(result.getNeedTicketCount()).isEqualTo(NEED_TICKET_COUNT);
            assertThat(result.getValidStartDate()).isEqualTo(START_DATE);
            assertThat(result.getValidEndDate()).isEqualTo(END_DATE);
        });
    }

    @Test
    void 상품_응모_당첨_테스트() {
        final var 티켓 = 티켓(회원());
        final var 티켓들 = List.of(티켓, 티켓, 티켓, 티켓, 티켓, 티켓);
        when(productRepository.findByIdWithPessimisticLock(PRODUCT_ID)).thenReturn(Optional.of(당첨되는_상품()));
        when(ticketRepository.countByMemberIdAndExpireDateGreaterThanEqual(eq(MEMBER_ID), any())).thenReturn(100L);
        when(ticketRepository.findAllByMemberIdAndExpireDateGreaterThanEqualAndIsUsed(eq(MEMBER_ID), any(), eq(false), any())).thenReturn(티켓들);
        when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(회원()));

        final var result = productService.applyProduct(회원토큰(), PRODUCT_ID);

        assertThat(result.isResult()).isTrue();
    }

    @Test
    void 상품_응모_실패_테스트() {
        when(productRepository.findByIdWithPessimisticLock(PRODUCT_ID)).thenReturn(Optional.of(당첨되지_않는_상품()));
        when(ticketRepository.countByMemberIdAndExpireDateGreaterThanEqual(MEMBER_ID, LocalDate.of(2025, 12, 31)))
                .thenReturn(100L);

        final var result = productService.applyProduct(회원토큰(), PRODUCT_ID);

        assertThat(result.isResult()).isFalse();
    }
}
