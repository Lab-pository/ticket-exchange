package com.ticketlounge.web.ticket.response;

import java.time.LocalDate;

import com.ticketlounge.domain.ticket.Ticket;

public record TicketResponse(String howToAcquire, LocalDate acquireDate, LocalDate expireDate) {

    public static TicketResponse from(Ticket ticket) {
        return new TicketResponse(ticket.getHowToAcquire(), ticket.getAcquireDate(), ticket.getExpireDate());
    }
}
