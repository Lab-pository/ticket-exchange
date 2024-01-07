package com.ticketexchange.ticket.application.port.in;

import java.util.List;

import com.ticketexchange.ticket.domain.Ticket;

public interface TicketsOfMemberQuery {

    List<Ticket> getTicketsOfMember(final Long memberId);
}
