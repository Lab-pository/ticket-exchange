package com.ticketexchange.fixture;

import org.springframework.test.util.ReflectionTestUtils;

import com.ticketexchange.domain.EarnedProduct;
import com.ticketexchange.domain.Member;
import com.ticketexchange.domain.Product;

public final class EarnedProductFixture {

    public static final Long EARNED_PRODUCT_ID = 1L;

    private EarnedProductFixture() {
    }

    public static EarnedProduct 획득한_경품(final Member 회원, final Product 상품) {
        final EarnedProduct earnedProduct = new EarnedProduct(회원, 상품);
        ReflectionTestUtils.setField(earnedProduct, "id", EARNED_PRODUCT_ID);
        return earnedProduct;
    }
}
