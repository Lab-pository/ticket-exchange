package com.ticketlounge.web.product.response;

import java.time.LocalDateTime;

import com.ticketlounge.domain.product.EarnedProduct;

public record EarnedProductResponse(Long earnedProductId, Long productId, String name, LocalDateTime createdAt) {

    public static EarnedProductResponse from(EarnedProduct earnedProduct) {
        return new EarnedProductResponse(earnedProduct.getId(), earnedProduct.getProductId(),
                earnedProduct.getProductName(), earnedProduct.getCreatedAt()
        );
    }
}
