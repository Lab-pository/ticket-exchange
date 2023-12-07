package com.ticketexchange.service.dto;

import com.ticketexchange.domain.Product;

public class ApplyProductDto {

    private boolean result;
    private ProductDto productDto;

    protected ApplyProductDto() {
    }

    public ApplyProductDto(boolean result, ProductDto productDto) {
        this.result = result;
        this.productDto = productDto;
    }

    public static ApplyProductDto of(boolean result, Product product) {
        return new ApplyProductDto(result, ProductDto.from(product));
    }

    public boolean isResult() {
        return result;
    }

    public ProductDto getProductDto() {
        return productDto;
    }
}
