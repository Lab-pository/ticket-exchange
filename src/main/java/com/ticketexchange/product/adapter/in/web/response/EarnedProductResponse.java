package com.ticketexchange.product.adapter.in.web.response;

import java.time.LocalDateTime;

import com.ticketexchange.product.domain.EarnedProduct;

public record EarnedProductResponse(Long earnedProductId, Long productId, String name, LocalDateTime createdAt) {

    public static EarnedProductResponse from(EarnedProduct earnedProduct) {
        return new EarnedProductResponse(earnedProduct.getId(), earnedProduct.getProductId(),
                earnedProduct.getProductName(), earnedProduct.getCreatedAt()
        );
    }
}
