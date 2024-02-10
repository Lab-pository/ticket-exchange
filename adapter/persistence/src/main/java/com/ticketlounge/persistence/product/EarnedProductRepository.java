package com.ticketlounge.persistence.product;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketlounge.persistence.product.entity.EarnedProductJpaEntity;

public interface EarnedProductRepository extends JpaRepository<EarnedProductJpaEntity, Long> {

    @EntityGraph(attributePaths = {"product"})
    List<EarnedProductJpaEntity> findAllByMemberId(final Long acquiredById);
}
