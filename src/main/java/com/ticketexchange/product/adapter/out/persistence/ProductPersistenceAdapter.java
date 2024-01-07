package com.ticketexchange.product.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticketexchange.product.application.port.out.ProductPort;
import com.ticketexchange.product.application.port.out.ProductQueryPort;
import com.ticketexchange.product.domain.Product;

@Repository
public class ProductPersistenceAdapter implements ProductQueryPort, ProductPort {

    private final ProductRepository productRepository;

    public ProductPersistenceAdapter(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(final Product product) {
        return ProductMapper.toDomain(productRepository.save(ProductMapper.toJpaEntity(product)));
    }

    @Override
    public Product getById(final Long id) {
        return ProductMapper.toDomain(productRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<Product> findAllByValid(final LocalDate now) {
        return productRepository.findAllByValidStartDateLessThanEqualAndValidEndDateGreaterThanEqual(now, now)
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }
}
