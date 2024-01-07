package com.ticketexchange.product.application;

import static com.ticketexchange.member.fixture.MemberFixture.MEMBER_ID;
import static com.ticketexchange.member.fixture.MemberFixture.회원;
import static com.ticketexchange.member.fixture.MemberFixture.회원토큰;
import static com.ticketexchange.product.fixture.EarnedProductFixture.획득한_경품;
import static com.ticketexchange.product.fixture.ProductFixture.상품;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.product.application.port.in.EarnedProductQuery;
import com.ticketexchange.product.application.port.out.EarnedProductQueryPort;
import com.ticketexchange.product.application.service.EarnedProductQueryService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EarnedProductQueryService.class)
class EarnedProductQueryServiceTest {

    @Autowired
    EarnedProductQuery earnedProductQuery;

    @MockBean
    EarnedProductQueryPort earnedProductQueryPort;

    @Test
    void 획득한_상품_조회_테스트() {
        final var 획득한_경품들 = List.of(획득한_경품(회원(), 상품()));
        when(earnedProductQueryPort.findAllByMemberId(MEMBER_ID)).thenReturn(획득한_경품들);

        final var result = earnedProductQuery.getEarnedProductsOfMember(회원토큰().getId());

        assertThat(result).hasSize(획득한_경품들.size());
    }
}
