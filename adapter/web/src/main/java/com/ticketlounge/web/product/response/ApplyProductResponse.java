package com.ticketlounge.web.product.response;

import com.ticketlounge.domain.product.EarnedProduct;

public record ApplyProductResponse(boolean result, ProductResponse product) {

    public static ApplyProductResponse from(final EarnedProduct earnedProduct) {
        if (earnedProduct == null) {
            return new ApplyProductResponse(false, null);
        }
        return new ApplyProductResponse(true, ProductResponse.from(earnedProduct.getProduct()));
    }
}
