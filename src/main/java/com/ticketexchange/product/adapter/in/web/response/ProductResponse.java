package com.ticketexchange.product.adapter.in.web.response;

import java.time.LocalDate;

import com.ticketexchange.product.domain.Product;

public record ProductResponse(
        Long productId,
        String name,
        LocalDate validStartDate,
        LocalDate validEndDate,
        int totalQuantity,
        int remainQuantity,
        int needTicketCount,
        double probability
) {

    public static ProductResponse from(final Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getValidStartDate(),
                product.getValidEndDate(), product.getTotalQuantity(), product.getRemainQuantity(),
                product.getNeedTicketCount(), product.getProbability()
        );
    }
}
