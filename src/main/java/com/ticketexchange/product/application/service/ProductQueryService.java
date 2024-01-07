package com.ticketexchange.product.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.product.application.port.in.ProductQuery;
import com.ticketexchange.product.application.port.out.ProductQueryPort;
import com.ticketexchange.product.domain.Product;

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
