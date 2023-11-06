package com.ticketexchange.controller.dto;

import java.time.LocalDateTime;

import com.ticketexchange.service.dto.EarnedProductDto;

public class EarnedProductResponse {

    private Long earnedProductId;
    private Long productId;
    private String name;
    private LocalDateTime createdAt;

    protected EarnedProductResponse() {
    }

    public EarnedProductResponse(Long earnedProductId, Long productId, String name, LocalDateTime createdAt) {
        this.earnedProductId = earnedProductId;
        this.productId = productId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static EarnedProductResponse of(EarnedProductDto earnedProductDto) {
        return new EarnedProductResponse(earnedProductDto.getEarnedProductId(), earnedProductDto.getProductId(),
                earnedProductDto.getName(), earnedProductDto.getCreatedAt()
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
