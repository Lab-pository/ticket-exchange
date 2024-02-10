package com.ticketlounge.application.ticket.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketlounge.application.ticket.port.in.TicketsOfMemberQuery;
import com.ticketlounge.application.ticket.port.out.TicketQueryPort;
import com.ticketlounge.domain.ticket.Ticket;

@Service
@Transactional
public class TicketsOfMemberQueryService implements TicketsOfMemberQuery {

    private final TicketQueryPort ticketQueryPort;

    public TicketsOfMemberQueryService(final TicketQueryPort ticketQueryPort) {
        this.ticketQueryPort = ticketQueryPort;
    }

    @Override
    public List<Ticket> getTicketsOfMember(final Long memberId) {
        return ticketQueryPort.findAllByMemberId(memberId);
    }
}
