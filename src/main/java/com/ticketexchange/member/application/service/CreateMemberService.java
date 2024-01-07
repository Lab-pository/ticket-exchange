package com.ticketexchange.member.application.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.member.application.port.in.CreateMemberUseCase;
import com.ticketexchange.member.application.port.out.SaveMemberPort;
import com.ticketexchange.member.domain.Member;
import com.ticketexchange.ticket.application.port.out.SaveTicketPort;
import com.ticketexchange.ticket.domain.Ticket;

@Service
@Transactional
public class CreateMemberService implements CreateMemberUseCase {

    private final SaveMemberPort saveMemberPort;
    private final SaveTicketPort saveTicketPort;

    public CreateMemberService(final SaveMemberPort saveMemberPort, final SaveTicketPort saveTicketPort) {
        this.saveMemberPort = saveMemberPort;
        this.saveTicketPort = saveTicketPort;
    }

    @Override
    public Member createMember(final CreateMemberCommand createMemberCommand) {
        final Member member = saveMemberPort.save(createMemberCommand.toEntity());
        createWelcomeTickets(member);
        return member;
    }

    private void createWelcomeTickets(final Member member) {
        final Ticket ticket = new Ticket(member.getId());
        List<Ticket> tickets = IntStream.range(0, 10).mapToObj(i -> ticket).toList();
        saveTicketPort.saveAll(tickets);
    }
}
