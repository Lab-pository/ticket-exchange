package com.ticketlounge.application.product.port.in;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.Product;

@Component
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
