package com.ticketexchange.product.application.port.out;

import java.time.LocalDate;
import java.util.List;

import com.ticketexchange.product.domain.Product;

public interface ProductQueryPort {

    Product getById(final Long id);

    List<Product> findAllByValid(LocalDate now);
}
