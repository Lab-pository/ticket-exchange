package com.ticketexchange.service.dto;

import java.time.LocalDateTime;

import com.ticketexchange.domain.EarnedProduct;
import com.ticketexchange.domain.Product;

public class EarnedProductDto {

    private Long earnedProductId;
    private Long productId;
    private String name;
    private LocalDateTime createdAt;

    protected EarnedProductDto() {
    }

    public EarnedProductDto(Long earnedProductId, Long productId, String name, LocalDateTime createdAt) {
        this.earnedProductId = earnedProductId;
        this.productId = productId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static EarnedProductDto of(EarnedProduct earnedProduct) {
        Product product = earnedProduct.getProduct();
        return new EarnedProductDto(earnedProduct.getId(), product.getId(), product.getName(),
                earnedProduct.getCreatedAt()
        );
    }

    public Long getEarnedProductId() {
        return earnedProductId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
