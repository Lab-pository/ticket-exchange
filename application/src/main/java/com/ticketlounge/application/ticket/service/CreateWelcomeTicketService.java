package com.ticketlounge.application.ticket.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ticketlounge.application.ticket.port.out.SaveTicketPort;
import com.ticketlounge.domain.member.MemberCreateEvent;
import com.ticketlounge.domain.ticket.Ticket;

@Service
public class CreateWelcomeTicketService {

    private final SaveTicketPort saveTicketPort;

    public CreateWelcomeTicketService(final SaveTicketPort saveTicketPort) {
        this.saveTicketPort = saveTicketPort;
    }

    @Async
    @TransactionalEventListener(MemberCreateEvent.class)
    public void createWelcomeTickets(final MemberCreateEvent memberCreateEvent) {
        List<Ticket> tickets = IntStream.range(0, 10).mapToObj(i -> new Ticket(memberCreateEvent.memberId())).toList();
        saveTicketPort.saveAll(tickets);
    }
}
