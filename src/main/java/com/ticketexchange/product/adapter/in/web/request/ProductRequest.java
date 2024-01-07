package com.ticketexchange.product.adapter.in.web.request;

import java.time.LocalDate;

import com.ticketexchange.product.application.port.in.CreateProductUseCase.CreateProductCommand;

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
