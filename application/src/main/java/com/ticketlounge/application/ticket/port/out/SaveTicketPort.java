package com.ticketlounge.application.ticket.port.out;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.ticket.Ticket;

@Component
public interface SaveTicketPort {

    List<Ticket> saveAll(final List<Ticket> tickets);
}
