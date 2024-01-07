package com.ticketexchange.product.adapter.out.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "earned_product")
public class EarnedProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "earned_product_id")
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    private LocalDateTime createdAt;

    protected EarnedProductJpaEntity() {
    }

    public EarnedProductJpaEntity(final Long memberId, final ProductJpaEntity product) {
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

    public ProductJpaEntity getProduct() {
        return this.product;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}

