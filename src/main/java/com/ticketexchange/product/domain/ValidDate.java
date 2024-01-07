package com.ticketexchange.product.domain;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ValidDate {

    @Column(name = "valid_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "valid_end_date", nullable = false)
    private LocalDate endDate;

    protected ValidDate() {
    }

    public ValidDate(final LocalDate startDate, final LocalDate endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(final LocalDate startDate, final LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isBetweenInclusive(final LocalDate now) {
        return !(startDate.isAfter(now) || now.isAfter(endDate));
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ValidDate validDate = (ValidDate) o;
        return Objects.equals(startDate, validDate.startDate) && Objects.equals(endDate,
                validDate.endDate
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
