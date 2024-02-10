package com.ticketlounge.persistence.product;

import com.ticketlounge.persistence.product.entity.ProductJpaEntity;
import com.ticketlounge.domain.product.Product;

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
