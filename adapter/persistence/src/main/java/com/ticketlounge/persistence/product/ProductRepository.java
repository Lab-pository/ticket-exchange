package com.ticketlounge.persistence.product;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ticketlounge.persistence.product.entity.ProductJpaEntity;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {

    @Query("select p from ProductJpaEntity p where p.endDate >= :endDate and p.startDate <= :startDate")
    List<ProductJpaEntity> findAllByValidStartDateLessThanEqualAndValidEndDateGreaterThanEqual(
            final LocalDate startDate,
            final LocalDate endDate
    );
}
