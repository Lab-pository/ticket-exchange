package com.ticketexchange.product.adapter.out.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private Integer totalQuantity;

    private Integer remainQuantity;

    private Integer needTicketCount;

    @Column(name = "valid_start_date")
    private LocalDate startDate;

    @Column(name = "valid_end_date")
    private LocalDate endDate;

    private Double probability;

    protected ProductJpaEntity() {
    }

    public ProductJpaEntity(
            final Long id,
            final String name,
            final Integer totalQuantity,
            final Integer needTicketCount,
            final LocalDate startDate,
            final LocalDate endDate,
            final Double probability
    ) {
        this.id = id;
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.remainQuantity = totalQuantity;
        this.needTicketCount = needTicketCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.probability = probability;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getTotalQuantity() {
        return this.totalQuantity;
    }

    public Integer getRemainQuantity() {
        return this.remainQuantity;
    }

    public Integer getNeedTicketCount() {
        return this.needTicketCount;
    }

    public LocalDate getValidStartDate() {
        return this.startDate;
    }

    public LocalDate getValidEndDate() {
        return this.endDate;
    }

    public Double getProbability() {
        return this.probability;
    }

    public void decreaseRemainQuantity() {
        if (this.remainQuantity > 0) {
            this.remainQuantity--;
        }
    }
}
