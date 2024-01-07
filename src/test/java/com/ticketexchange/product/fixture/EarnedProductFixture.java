package com.ticketexchange.product.fixture;

import com.ticketexchange.member.domain.Member;
import com.ticketexchange.product.domain.EarnedProduct;
import com.ticketexchange.product.domain.Product;

public final class EarnedProductFixture {

    public static final Long EARNED_PRODUCT_ID = 1L;

    private EarnedProductFixture() {
    }

    public static EarnedProduct 획득한_경품(final Member 회원, final Product 상품) {
        return new EarnedProduct(EARNED_PRODUCT_ID, 회원.getId(), 상품);
    }
}
