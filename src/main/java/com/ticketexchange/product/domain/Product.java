package com.ticketexchange.product.domain;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Product {

    private final Long id;
    private final String name;
    private final Quantity totalQuantity;
    private Quantity remainQuantity;
    private final int needTicketCount;
    private final ValidDate validDate;
    private final Probability probability;

    public Product(
            final Long id,
            final String name,
            final int totalQuantity,
            final int needTicketCount,
            final LocalDate startDate,
            final LocalDate endDate,
            final Double probability
    ) {
        this.id = id;
        this.name = name;
        this.totalQuantity = new Quantity(totalQuantity);
        this.remainQuantity = new Quantity(totalQuantity);
        this.needTicketCount = needTicketCount;
        this.validDate = new ValidDate(startDate, endDate);
        this.probability = new Probability(probability);
    }

    public boolean canApply(final LocalDate now) {
        return remainQuantity.isEnough() && validDate.isBetweenInclusive(now);
    }

    public void decreaseRemainQuantity() {
        if (remainQuantity.isEnough()) {
            this.remainQuantity = this.remainQuantity.decrease();
        }
    }

    public boolean isWin() {
        final double randomProbabilityValue = ThreadLocalRandom.current().nextDouble();
        return this.probability.getValue() >= randomProbabilityValue;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getTotalQuantity() {
        return this.totalQuantity.getValue();
    }

    public int getRemainQuantity() {
        return this.remainQuantity.getValue();
    }

    public int getNeedTicketCount() {
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
