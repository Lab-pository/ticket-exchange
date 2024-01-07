package com.ticketexchange.ticket.adapter.in.web.request;

import com.ticketexchange.ticket.application.port.in.CreateTicketUseCase.CreateTicketCommand;

public record TicketRequest(String howToAcquire, int count) {

    public CreateTicketCommand toCreateTicketCommand() {
        return new CreateTicketCommand(howToAcquire, count);
    }
}
