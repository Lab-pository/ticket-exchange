package com.ticketexchange.product.application.port.in;

import java.util.List;

import com.ticketexchange.product.domain.Product;

public interface ProductQuery {

    List<Product> findAllByValid();
}
