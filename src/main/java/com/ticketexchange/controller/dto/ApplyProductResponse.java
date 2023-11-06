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

    public static ApplyProductResponse of(ApplyProductDto applyProductDto) {
        return new ApplyProductResponse(applyProductDto.isResult(),
                ProductResponse.of(applyProductDto.getProductDto())
        );
    }

    public boolean isResult() {
        return result;
    }

    public ProductResponse getProduct() {
        return product;
    }
}
