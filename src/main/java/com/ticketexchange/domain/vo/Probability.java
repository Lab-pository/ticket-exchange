package com.ticketexchange.domain.vo;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Probability {

    @Column(name = "probability", nullable = false)
    private double value;

    protected Probability() {
    }

    public Probability(final double value) {
        validate(value);
        this.value = value;
    }

    private void validate(final double value) {
        if (value < 0.0 || value > 1.0) {
            throw new IllegalArgumentException();
        }
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Probability that = (Probability) o;
        return Double.compare(value, that.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
