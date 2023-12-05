package com.ticketexchange.controller.dto;

import java.time.LocalDate;

import com.ticketexchange.service.dto.CreateProductDto;

public class ProductRequest {

    private String name;
    private int needTicketCount;
    private int totalQuantity;
    private LocalDate startDate;
    private LocalDate endDate;
    private double probability;

    protected ProductRequest() {
    }

    public ProductRequest(
            String name, int needTicketCount, int totalQuantity, LocalDate startDate, LocalDate endDate,
            double probability
    ) {
        this.name = name;
        this.needTicketCount = needTicketCount;
        this.totalQuantity = totalQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.probability = probability;
    }

    public CreateProductDto toCreateProductDto() {
        return new CreateProductDto(name, needTicketCount, totalQuantity, startDate, endDate, probability);
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

    public double getProbability() {
        return this.probability;
    }
}
