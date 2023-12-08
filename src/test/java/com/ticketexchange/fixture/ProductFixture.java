package com.ticketexchange.fixture;

import java.time.LocalDate;

import org.springframework.test.util.ReflectionTestUtils;

import com.ticketexchange.controller.dto.ProductRequest;
import com.ticketexchange.domain.Product;
import com.ticketexchange.service.dto.CreateProductDto;

public final class ProductFixture {

    public static final Long PRODUCT_ID = 1L;
    public static final String PRODUCT_NAME = "상품";
    public static final int NEED_TICKET_COUNT = 5;
    public static final int TOTAL_QUANTITY = 1000;
    public static final LocalDate START_DATE = LocalDate.of(2023, 1, 1);
    public static final LocalDate END_DATE = LocalDate.of(2024, 12, 31);
    public static final double PROBABILITY = 0.2;
    public static final double FULL_PROBABILITY = 1;
    public static final double ZERO_PROBABILITY = 0;

    private ProductFixture() {
    }

    public static ProductRequest 상품_생성_요청() {
        return new ProductRequest(PRODUCT_NAME, NEED_TICKET_COUNT, TOTAL_QUANTITY, START_DATE, END_DATE, PROBABILITY);
    }

    public static ProductRequest 상품_생성_요청(
            final String name, final int needTicketCount, final int totalQuantity, final LocalDate startDate,
            final LocalDate endDate, final double probability
    ) {
        return new ProductRequest(name, needTicketCount, totalQuantity, startDate, endDate, probability);
    }

    public static ProductRequest 당첨되는_상품_생성_요청() {
        return new ProductRequest(PRODUCT_NAME, NEED_TICKET_COUNT, TOTAL_QUANTITY, START_DATE, END_DATE,
                FULL_PROBABILITY
        );
    }

    public static ProductRequest 당첨되지_않는_상품_생성_요청() {
        return new ProductRequest(PRODUCT_NAME, NEED_TICKET_COUNT, TOTAL_QUANTITY, START_DATE, END_DATE,
                ZERO_PROBABILITY
        );
    }

    public static CreateProductDto 상품_생성_DTO() {
        return new CreateProductDto(PRODUCT_NAME, NEED_TICKET_COUNT, TOTAL_QUANTITY, START_DATE, END_DATE, PROBABILITY);

    }

    public static Product 상품() {
        final Product product = new Product(PRODUCT_NAME, TOTAL_QUANTITY, NEED_TICKET_COUNT, START_DATE, END_DATE,
                PROBABILITY
        );
        ReflectionTestUtils.setField(product, "id", PRODUCT_ID);
        return product;
    }

    public static Product 당첨되는_상품() {
        final Product product = new Product(PRODUCT_NAME, TOTAL_QUANTITY, NEED_TICKET_COUNT, START_DATE, END_DATE,
                FULL_PROBABILITY
        );
        ReflectionTestUtils.setField(product, "id", PRODUCT_ID);
        return product;
    }

    public static Product 당첨되지_않는_상품() {
        final Product product = new Product(PRODUCT_NAME, TOTAL_QUANTITY, NEED_TICKET_COUNT, START_DATE, END_DATE,
                ZERO_PROBABILITY
        );
        ReflectionTestUtils.setField(product, "id", PRODUCT_ID);
        return product;
    }

}
