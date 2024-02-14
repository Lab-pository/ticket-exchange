package com.ticketlounge.persistence.ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketlounge.persistence.ticket.entity.TicketJpaEntity;

public interface TicketRepository extends JpaRepository<TicketJpaEntity, Long> {

    List<TicketJpaEntity> findAllByMemberId(final Long memberId);
}
