package com.ticketlounge.application.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketlounge.application.product.port.in.CreateProductUseCase;
import com.ticketlounge.application.product.port.out.ProductPort;
import com.ticketlounge.domain.product.Product;

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
