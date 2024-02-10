package com.ticketlounge.application.ticket.port.out;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.ticket.Ticket;

@Component
public interface TicketQueryPort {

    List<Ticket> findAllByMemberId(final Long memberId);

    List<Ticket> findAllByMemberIdWithValid(final Long memberId, final LocalDate now);
}
