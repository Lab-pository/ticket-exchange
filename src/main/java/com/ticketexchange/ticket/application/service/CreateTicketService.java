package com.ticketexchange.ticket.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.ticket.application.port.in.CreateTicketUseCase;
import com.ticketexchange.ticket.application.port.out.SaveTicketPort;
import com.ticketexchange.ticket.domain.Ticket;

@Service
@Transactional
public class CreateTicketService implements CreateTicketUseCase {

    private final SaveTicketPort saveTicketPort;

    public CreateTicketService(final SaveTicketPort saveTicketPort) {
        this.saveTicketPort = saveTicketPort;
    }

    @Override
    public Ticket createTicket(final MemberToken token, final CreateTicketCommand createTicketCommand) {
        final Ticket ticket = createTicketCommand.toDomain(token.getId(), LocalDate.now());
        final List<Ticket> tickets = IntStream.range(0, createTicketCommand.count()).mapToObj(i -> ticket).toList();
        saveTicketPort.saveAll(tickets);

        return ticket;
    }
}
