package com.ticketexchange.ticket.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketJpaEntity, Long> {

    long countByMemberIdAndExpireDateGreaterThanEqual(Long memberId, LocalDate expireDate);

    List<TicketJpaEntity> findAllByMemberIdAndExpireDateGreaterThanEqualAndIsUsed(
            final Long memberId, final LocalDate expireDate, final boolean used, final Sort sort
    );

    List<TicketJpaEntity> findAllByMemberId(final Long memberId);
}
