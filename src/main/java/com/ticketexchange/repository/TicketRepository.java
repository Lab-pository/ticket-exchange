package com.ticketexchange.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketexchange.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    long countByMemberIdAndExpireDateGreaterThanEqual(Long memberId, LocalDate expireDate);

    List<Ticket> findAllByMemberIdAndExpireDateGreaterThanEqualAndIsUsed(
            Long memberId, LocalDate expireDate, boolean used, Sort sort
    );

    List<Ticket> findAllByMemberId(Long memberId);
}
