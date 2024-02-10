package com.ticketlounge.application.product.port.out;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.Product;

@Component
public interface ProductQueryPort {

    Product getById(final Long id);

    List<Product> findAllByValid(LocalDate now);
}
