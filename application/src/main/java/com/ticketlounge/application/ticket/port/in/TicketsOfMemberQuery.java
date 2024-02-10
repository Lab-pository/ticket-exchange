package com.ticketlounge.application.ticket.port.in;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.ticket.Ticket;

@Component
public interface TicketsOfMemberQuery {

    List<Ticket> getTicketsOfMember(final Long memberId);
}
