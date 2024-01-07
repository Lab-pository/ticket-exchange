package com.ticketexchange.product.domain;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Product {

    private Long id;
    private String name;
    private Integer totalQuantity;
    private Integer remainQuantity;
    private Integer needTicketCount;
    private ValidDate validDate;
    private Probability probability;

    public Product(
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
        this.validDate = new ValidDate(startDate, endDate);
        this.probability = new Probability(probability);
    }

    public boolean canApply(final LocalDate now) {
        return isEnoughQuantity() && validDate.isBetweenInclusive(now);
    }

    public void decreaseRemainQuantity() {
        if (isEnoughQuantity()) {
            this.remainQuantity--;
        }
    }

    public boolean isWin() {
        final double randomProbabilityValue = ThreadLocalRandom.current().nextDouble();
        return this.probability.getValue() >= randomProbabilityValue;
    }

    public boolean isEnoughQuantity() {
        return this.remainQuantity > 0;
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
}
