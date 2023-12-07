package com.ticketexchange.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.ticketexchange.domain.vo.Probability;
import com.ticketexchange.domain.vo.ValidDate;

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

    @Embedded
    private ValidDate validDate;

    @Embedded
    private Probability probability;

    protected Product() {
    }

    public Product(
            String name,
            Integer totalQuantity,
            Integer needTicketCount,
            LocalDate startDate,
            LocalDate endDate,
            Double probability
    ) {
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.remainQuantity = totalQuantity;
        this.needTicketCount = needTicketCount;
        this.validDate = new ValidDate(startDate, endDate);
        this.probability = new Probability(probability);
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
        return this.validDate.getStartDate();
    }

    public LocalDate getValidEndDate() {
        return this.validDate.getEndDate();
    }

    public Double getProbability() {
        return this.probability.getValue();
    }

    public void decreaseRemainQuantity() {
        if (this.remainQuantity > 0) {
            this.remainQuantity--;
        }
    }
}
