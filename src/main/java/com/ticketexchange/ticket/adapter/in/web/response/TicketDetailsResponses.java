package com.ticketexchange.ticket.adapter.in.web.response;

import java.util.List;

import com.ticketexchange.ticket.domain.Ticket;

public record TicketDetailsResponses(
        long totalTicketCount,
        long useTicketCount,
        long validTicketCount,
        List<TicketDetailsResponse> tickets
) {

    public static TicketDetailsResponses from(List<Ticket> tickets) {
        long totalTicketCount = tickets.size();
        long useTicketCount = tickets.stream().filter(Ticket::isUsed).count();
        long validTicketCount = totalTicketCount - useTicketCount;
        return new TicketDetailsResponses(totalTicketCount, useTicketCount, validTicketCount,
                tickets.stream().map(TicketDetailsResponse::from).toList()
        );
    }
}
