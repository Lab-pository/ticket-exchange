package com.ticketlounge.application.product;

import static com.ticketlounge.application.fixture.ProductFixture.END_DATE;
import static com.ticketlounge.application.fixture.ProductFixture.NEED_TICKET_COUNT;
import static com.ticketlounge.application.fixture.ProductFixture.PROBABILITY;
import static com.ticketlounge.application.fixture.ProductFixture.PRODUCT_ID;
import static com.ticketlounge.application.fixture.ProductFixture.PRODUCT_NAME;
import static com.ticketlounge.application.fixture.ProductFixture.START_DATE;
import static com.ticketlounge.application.fixture.ProductFixture.상품;
import static com.ticketlounge.application.fixture.ProductFixture.상품_생성_커맨드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketlounge.application.product.port.in.CreateProductUseCase;
import com.ticketlounge.application.product.port.out.ProductPort;
import com.ticketlounge.application.product.service.CreateProductService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CreateProductService.class)
class CreateProductServiceTest {

    @Autowired
    CreateProductUseCase createProductUseCase;

    @MockBean
    ProductPort productPort;

    @Test
    void 상품_등록_테스트() {
        when(productPort.save(any())).thenReturn(상품());

        final var result = createProductUseCase.registerProduct(상품_생성_커맨드());

        assertAll(() -> {
            assertThat(result.getId()).isEqualTo(PRODUCT_ID);
            assertThat(result.getName()).isEqualTo(PRODUCT_NAME);
            assertThat(result.getProbability()).isEqualTo(PROBABILITY);
            assertThat(result.getNeedTicketCount()).isEqualTo(NEED_TICKET_COUNT);
            assertThat(result.getValidStartDate()).isEqualTo(START_DATE);
            assertThat(result.getValidEndDate()).isEqualTo(END_DATE);
        });
    }
}
