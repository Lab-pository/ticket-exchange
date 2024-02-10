package com.ticketlounge.application.product.port.out;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.Product;

@Component
public interface ProductPort {

    Product save(final Product product);

}
