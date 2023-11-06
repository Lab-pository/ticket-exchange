package com.ticketexchange.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private Integer totalQuantity;

    private Integer remainQuantity;

    private Integer needTicketCount;

    private LocalDate validStartDate;

    private LocalDate validEndDate;

    private Double probability;

    protected Product() {
    }

    public Product(
            String name, Integer totalQuantity, Integer needTicketCount, LocalDate validStartDate,
            LocalDate validEndDate, Double probability
    ) {
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.remainQuantity = totalQuantity;
        this.needTicketCount = needTicketCount;
        this.validStartDate = validStartDate;
        this.validEndDate = validEndDate;
        this.probability = probability;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Integer getRemainQuantity() {
        return remainQuantity;
    }

    public Integer getNeedTicketCount() {
        return needTicketCount;
    }

    public LocalDate getValidStartDate() {
        return validStartDate;
    }

    public LocalDate getValidEndDate() {
        return validEndDate;
    }

    public Double getProbability() {
        return probability;
    }

    public void decreaseRemainQuantity() {
        if (this.remainQuantity > 0) {
            this.remainQuantity--;
        }
    }
}
