package com.ticketlounge.persistence.ticket;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticketlounge.application.ticket.port.out.SaveTicketPort;
import com.ticketlounge.application.ticket.port.out.TicketQueryPort;
import com.ticketlounge.domain.ticket.Ticket;

@Repository
public class TicketPersistenceAdapter implements SaveTicketPort, TicketQueryPort {

    private final TicketRepository ticketRepository;

    public TicketPersistenceAdapter(final TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> saveAll(final List<Ticket> tickets) {
        return ticketRepository.saveAll(tickets.stream().map(TicketMapper::toJpaEntity).toList())
                .stream().map(TicketMapper::toDomain).toList();
    }

    @Override
    public List<Ticket> findAllByMemberId(final Long memberId) {
        return ticketRepository.findAllByMemberId(memberId).stream().map(TicketMapper::toDomain).toList();
    }

    @Override
    public List<Ticket> findAllByMemberIdWithValid(final Long memberId, final LocalDate now) {
        return ticketRepository.findAllByMemberId(memberId).stream()
                .filter(t -> !t.isUsed() && t.getExpireDate().isAfter(now))
                .map(TicketMapper::toDomain).toList();
    }
}
