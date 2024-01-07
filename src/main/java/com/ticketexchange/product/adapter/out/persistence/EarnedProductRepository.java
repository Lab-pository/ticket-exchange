package com.ticketexchange.product.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketexchange.product.adapter.out.persistence.entity.EarnedProductJpaEntity;

public interface EarnedProductRepository extends JpaRepository<EarnedProductJpaEntity, Long> {

    @EntityGraph(attributePaths = {"product"})
    List<EarnedProductJpaEntity> findAllByMemberId(final Long acquiredById);
}
