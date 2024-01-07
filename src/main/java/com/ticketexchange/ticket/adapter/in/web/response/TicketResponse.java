package com.ticketexchange.ticket.adapter.in.web.response;

import java.time.LocalDate;

import com.ticketexchange.ticket.domain.Ticket;

public record TicketResponse(String howToAcquire, LocalDate acquireDate, LocalDate expireDate) {

    public static TicketResponse from(Ticket ticket) {
        return new TicketResponse(ticket.getHowToAcquire(), ticket.getAcquireDate(), ticket.getExpireDate());
    }
}
