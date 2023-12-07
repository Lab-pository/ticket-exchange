package com.ticketexchange.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.domain.Member;
import com.ticketexchange.domain.Ticket;
import com.ticketexchange.repository.MemberRepository;
import com.ticketexchange.repository.TicketRepository;
import com.ticketexchange.service.dto.CreateTicketDto;
import com.ticketexchange.service.dto.TicketDetailsDto;
import com.ticketexchange.service.dto.TicketDto;

@Service
@Transactional
public class TicketService {

    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;

    public TicketService(final MemberRepository memberRepository, final TicketRepository ticketRepository) {
        this.memberRepository = memberRepository;
        this.ticketRepository = ticketRepository;
    }

    public TicketDto createTicket(final MemberToken token, final CreateTicketDto createTicketDto) {
        final Member member = memberRepository.findById(token.getId()).orElseThrow(IllegalArgumentException::new);
        final Ticket ticket = createTicketDto.toEntity(member, LocalDate.now());
        final List<Ticket> tickets = IntStream.range(0, createTicketDto.getCount()).mapToObj(i -> ticket).toList();
        ticketRepository.saveAll(tickets);

        return TicketDto.from(ticket);
    }

    public List<TicketDetailsDto> findAllTicketsByMember(final MemberToken token) {
        final List<Ticket> tickets = ticketRepository.findAllByMemberId(token.getId());

        return tickets.stream().map(TicketDetailsDto::from).toList();
    }
}
