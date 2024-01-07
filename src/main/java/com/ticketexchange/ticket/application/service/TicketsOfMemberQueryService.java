package com.ticketexchange.ticket.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.ticket.application.port.in.TicketsOfMemberQuery;
import com.ticketexchange.ticket.application.port.out.TicketQueryPort;
import com.ticketexchange.ticket.domain.Ticket;

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
