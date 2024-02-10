package com.ticketlounge.web.product.request;

import java.time.LocalDate;

import com.ticketlounge.application.product.port.in.CreateProductUseCase.CreateProductCommand;

public record ProductRequest(
        String name,
        int needTicketCount,
        int totalQuantity,
        LocalDate startDate,
        LocalDate endDate,
        double probability
) {

    public CreateProductCommand toCreateProductCommand() {
        return new CreateProductCommand(name, needTicketCount, totalQuantity, startDate, endDate, probability);
    }
}
