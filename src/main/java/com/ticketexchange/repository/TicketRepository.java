package com.ticketexchange.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketexchange.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    long countByMemberIdAndExpireDateGreaterThanEqual(Long memberId, LocalDate expireDate);

    List<Ticket> findAllByMemberIdAndExpireDateGreaterThanEqualAndIsUsed(
            final Long memberId, final LocalDate expireDate, final boolean used, final Sort sort
    );

    List<Ticket> findAllByMemberId(final Long memberId);
}
