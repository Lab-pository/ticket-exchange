package com.ticketlounge.application.product;

import static com.ticketlounge.application.fixture.EarnedProductFixture.획득한_경품;
import static com.ticketlounge.application.fixture.MemberFixture.MEMBER_ID;
import static com.ticketlounge.application.fixture.MemberFixture.회원;
import static com.ticketlounge.application.fixture.MemberFixture.회원토큰;
import static com.ticketlounge.application.fixture.ProductFixture.PRODUCT_ID;
import static com.ticketlounge.application.fixture.ProductFixture.당첨되는_상품;
import static com.ticketlounge.application.fixture.ProductFixture.당첨되지_않는_상품;
import static com.ticketlounge.application.fixture.TicketFixture.티켓;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketlounge.application.product.port.in.ApplyProductUseCase;
import com.ticketlounge.application.product.port.out.EarnedProductPort;
import com.ticketlounge.application.product.port.out.ProductQueryPort;
import com.ticketlounge.application.product.service.ApplyProductService;
import com.ticketlounge.application.ticket.port.out.TicketQueryPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplyProductService.class)
class ApplyProductServiceTest {

    @Autowired
    ApplyProductUseCase applyProductUseCase;

    @MockBean
    ProductQueryPort productQueryPort;

    @MockBean
    TicketQueryPort ticketQueryPort;

    @MockBean
    EarnedProductPort earnedProductPort;

    @Test
    void 상품_응모_당첨_테스트() {
        final var 티켓 = 티켓(회원());
        final var 티켓들 = List.of(티켓, 티켓, 티켓, 티켓, 티켓, 티켓);
        when(productQueryPort.getById(PRODUCT_ID)).thenReturn(당첨되는_상품());
        when(ticketQueryPort.findAllByMemberIdWithValid(eq(MEMBER_ID), any())).thenReturn(티켓들);
        when(earnedProductPort.save(any())).thenReturn(획득한_경품(회원(), 당첨되는_상품()));

        final var result = applyProductUseCase.applyProduct(회원토큰().getId(), PRODUCT_ID);

        assertThat(result).isNotNull();
    }

    @Test
    void 상품_응모_실패_테스트() {
        when(productQueryPort.getById(PRODUCT_ID)).thenReturn(당첨되지_않는_상품());
        when(ticketQueryPort.findAllByMemberIdWithValid(eq(MEMBER_ID), any())).thenReturn(List.of());

        final var result = applyProductUseCase.applyProduct(회원토큰().getId(), PRODUCT_ID);

        assertThat(result).isNull();
    }
}
