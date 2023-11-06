package com.ticketexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketexchange.domain.EarnedProduct;

public interface EarnedProductRepository extends JpaRepository<EarnedProduct, Long> {

    @EntityGraph(attributePaths = {"product"})
    List<EarnedProduct> findAllByAcquiredById(Long acquiredById);
}
