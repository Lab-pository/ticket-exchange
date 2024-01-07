package com.ticketexchange.product.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.product.application.port.in.CreateProductUseCase;
import com.ticketexchange.product.application.port.out.ProductPort;
import com.ticketexchange.product.domain.Product;

@Service
@Transactional
public class CreateProductService implements CreateProductUseCase {

    private final ProductPort productPort;

    public CreateProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @Override
    public Product registerProduct(final CreateProductCommand createProductCommand) {
        return productPort.save(createProductCommand.toDomain());
    }
}
