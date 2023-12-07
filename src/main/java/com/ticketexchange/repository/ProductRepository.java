package com.ticketexchange.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.ticketexchange.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByIdWithPessimisticLock(final Long id);

    @Query("select p from Product p where p.validDate.endDate >= :endDate and p.validDate.startDate <= :startDate")
    List<Product> findAllByValidStartDateLessThanEqualAndValidEndDateGreaterThanEqual(
            final LocalDate startDate,
            final LocalDate endDate
    );
}
