package com.ticketlounge.application.product.port.in;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.Product;

@Component
public interface ProductQuery {

    List<Product> findAllByValid();
}
