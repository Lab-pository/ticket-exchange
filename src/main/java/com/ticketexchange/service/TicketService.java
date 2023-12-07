package com.ticketexchange.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public TicketService(MemberRepository memberRepository, TicketRepository ticketRepository) {
        this.memberRepository = memberRepository;
        this.ticketRepository = ticketRepository;
    }

    public TicketDto createTicket(MemberToken token, CreateTicketDto createTicketDto) {
        Member member = memberRepository.findById(token.getId()).orElseThrow(IllegalArgumentException::new);
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = createTicketDto.toEntity(member, LocalDate.now());
        for (int i = 0; i < createTicketDto.getCount(); i++) {
            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);
        return TicketDto.from(ticket);
    }

    public List<TicketDetailsDto> findAllTicketsByMember(MemberToken token) {
        List<Ticket> tickets = ticketRepository.findAllByMemberId(token.getId());
        return tickets.stream().map(TicketDetailsDto::from).toList();
    }
}
