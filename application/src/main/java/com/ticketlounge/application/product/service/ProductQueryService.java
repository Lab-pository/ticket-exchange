package com.ticketlounge.application.product.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketlounge.application.product.port.in.ProductQuery;
import com.ticketlounge.application.product.port.out.ProductQueryPort;
import com.ticketlounge.domain.product.Product;

@Service
@Transactional(readOnly = true)
public class ProductQueryService implements ProductQuery {

    private final ProductQueryPort productQueryPort;

    public ProductQueryService(final ProductQueryPort productQueryPort) {
        this.productQueryPort = productQueryPort;
    }

    @Override
    public List<Product> findAllByValid() {
        return productQueryPort.findAllByValid(LocalDate.now());
    }
}
