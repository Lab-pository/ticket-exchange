package com.ticketlounge.persistence.product;

import com.ticketlounge.persistence.product.entity.EarnedProductJpaEntity;
import com.ticketlounge.domain.product.EarnedProduct;

public final class EarnedProductMapper {

    private EarnedProductMapper() {
    }

    static EarnedProduct toDomain(final EarnedProductJpaEntity earnedProductJpaEntity) {
        return new EarnedProduct(
                earnedProductJpaEntity.getId(),
                earnedProductJpaEntity.getMemberId(),
                ProductMapper.toDomain(earnedProductJpaEntity.getProduct())
        );
    }

    static EarnedProductJpaEntity toJpaEntity(final EarnedProduct earnedProduct) {
        return new EarnedProductJpaEntity(
                earnedProduct.getMemberId(),
                ProductMapper.toJpaEntity(earnedProduct.getProduct())
        );
    }
}
