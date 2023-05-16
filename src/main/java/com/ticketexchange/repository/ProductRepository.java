package com.ticketexchange.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.ticketexchange.domain.Product;

import jakarta.persistence.LockModeType;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Product p where p.id = :id")
	Optional<Product> findByIdWithPessimisticLock(Long id);

	List<Product> findAllByValidStartDateAfterAndValidEndDateBefore(LocalDate startDate, LocalDate endDate);
}
