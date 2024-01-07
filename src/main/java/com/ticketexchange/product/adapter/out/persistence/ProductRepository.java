package com.ticketexchange.product.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.ticketexchange.product.adapter.out.persistence.entity.ProductJpaEntity;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductJpaEntity p where p.id = :id")
    Optional<ProductJpaEntity> findByIdWithPessimisticLock(final Long id);

    @Query("select p from ProductJpaEntity p where p.endDate >= :endDate and p.startDate <= :startDate")
    List<ProductJpaEntity> findAllByValidStartDateLessThanEqualAndValidEndDateGreaterThanEqual(
            final LocalDate startDate,
            final LocalDate endDate
    );
}
