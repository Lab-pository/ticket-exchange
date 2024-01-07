package com.ticketexchange.ticket.application.port.out;

import java.util.List;

import com.ticketexchange.ticket.domain.Ticket;

public interface SaveTicketPort {

    List<Ticket> saveAll(final List<Ticket> tickets);
}
