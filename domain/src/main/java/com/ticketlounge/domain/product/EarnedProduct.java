package com.ticketlounge.domain.product;

import java.time.LocalDateTime;

public class EarnedProduct {

    private final Long id;
    private final Long memberId;
    private final Product product;
    private final LocalDateTime createdAt;

    public EarnedProduct(final Long memberId, final Product product) {
        this(null, memberId, product);
    }

    public EarnedProduct(final Long id, final Long memberId, final Product product) {
        this.id = id;
        this.memberId = memberId;
        this.product = product;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public Product getProduct() {
        return this.product;
    }

    public Long getProductId() {
        return this.product.getId();
    }

    public String getProductName() {
        return this.product.getName();
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
