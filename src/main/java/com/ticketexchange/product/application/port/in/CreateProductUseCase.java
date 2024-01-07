package com.ticketexchange.product.application.port.in;

import java.time.LocalDate;

import com.ticketexchange.product.domain.Product;

public interface CreateProductUseCase {

    Product registerProduct(final CreateProductCommand createProductCommand);

    record CreateProductCommand(
            String name,
            int needTicketCount,
            int totalQuantity,
            LocalDate startDate,
            LocalDate endDate,
            double probability
    ) {
        public Product toDomain() {
            return new Product(null, name, totalQuantity, needTicketCount, startDate, endDate, probability);
        }
    }
}
