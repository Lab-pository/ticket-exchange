package com.ticketexchange.controller.dto;

import com.ticketexchange.service.dto.ApplyProductDto;

public class ApplyProductResponse {

    private boolean result;
    private ProductResponse product;

    protected ApplyProductResponse() {
    }

    public ApplyProductResponse(boolean result, ProductResponse product) {
        this.result = result;
        this.product = product;
    }

    public static ApplyProductResponse from(ApplyProductDto applyProductDto) {
        return new ApplyProductResponse(applyProductDto.isResult(),
                ProductResponse.from(applyProductDto.getProductDto())
        );
    }

    public boolean isResult() {
        return result;
    }

    public ProductResponse getProduct() {
        return product;
    }
}
