package com.ticketexchange.service.dto;

import java.time.LocalDate;

import com.ticketexchange.domain.Product;

public class CreateProductDto {

    private String name;
    private int needTicketCount;
    private int totalQuantity;
    private LocalDate startDate;
    private LocalDate endDate;
    private double probability;

    protected CreateProductDto() {
    }

    public CreateProductDto(
            String name, int needTicketCount, int totalQuantity, LocalDate startDate,
            LocalDate endDate, double probability
    ) {
        this.name = name;
        this.needTicketCount = needTicketCount;
        this.totalQuantity = totalQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.probability = probability;
    }

    public Product toEntity() {
        return new Product(name, totalQuantity, needTicketCount, startDate, endDate, probability);
    }

    public String getName() {
        return name;
    }

    public int getNeedTicketCount() {
        return needTicketCount;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
