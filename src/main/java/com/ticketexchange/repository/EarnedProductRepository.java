package com.ticketexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketexchange.domain.EarnedProduct;

public interface EarnedProductRepository extends JpaRepository<EarnedProduct, Long> {
}
