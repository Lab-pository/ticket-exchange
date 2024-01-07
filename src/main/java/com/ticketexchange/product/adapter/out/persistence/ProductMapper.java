package com.ticketexchange.product.adapter.out.persistence;

import com.ticketexchange.product.adapter.out.persistence.entity.ProductJpaEntity;
import com.ticketexchange.product.domain.Product;

public final class ProductMapper {

    private ProductMapper() {
    }

    static Product toDomain(final ProductJpaEntity productJpaEntity) {
        return new Product(
                productJpaEntity.getId(),
                productJpaEntity.getName(),
                productJpaEntity.getTotalQuantity(),
                productJpaEntity.getNeedTicketCount(),
                productJpaEntity.getValidStartDate(),
                productJpaEntity.getValidEndDate(),
                productJpaEntity.getProbability()
        );
    }

    static ProductJpaEntity toJpaEntity(final Product product) {
        return new ProductJpaEntity(
                product.getId(),
               product.getName(),
               product.getTotalQuantity(),
               product.getNeedTicketCount(),
               product.getValidStartDate(),
               product.getValidEndDate(),
               product.getProbability()
        );
    }
}
