package com.ticketexchange.ticket.application.port.out;

import java.time.LocalDate;
import java.util.List;

import com.ticketexchange.ticket.domain.Ticket;

public interface TicketQueryPort {

    List<Ticket> findAllByMemberId(final Long memberId);

    List<Ticket> findAllByMemberIdWithValid(final Long memberId, final LocalDate now);
}
