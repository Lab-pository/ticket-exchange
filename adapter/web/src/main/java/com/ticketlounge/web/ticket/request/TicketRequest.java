package com.ticketlounge.web.ticket.request;

import com.ticketlounge.application.ticket.port.in.CreateTicketUseCase.CreateTicketCommand;

public record TicketRequest(String howToAcquire, int count) {

    public CreateTicketCommand toCreateTicketCommand() {
        return new CreateTicketCommand(howToAcquire, count);
    }
}
