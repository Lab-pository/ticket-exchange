package com.ticketexchange.product.adapter.out.persistence;

import com.ticketexchange.product.adapter.out.persistence.entity.EarnedProductJpaEntity;
import com.ticketexchange.product.domain.EarnedProduct;

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
