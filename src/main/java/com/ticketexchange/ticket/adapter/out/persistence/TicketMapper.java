package com.ticketexchange.ticket.adapter.out.persistence;

import com.ticketexchange.ticket.domain.Ticket;

public final class TicketMapper {

    private TicketMapper() {
    }

    static Ticket toDomain(final TicketJpaEntity ticketJpaEntity) {
        return new Ticket(
                ticketJpaEntity.getId(),
                ticketJpaEntity.getMemberId(),
                ticketJpaEntity.getHowToAcquire(),
                ticketJpaEntity.getAcquireDate(),
                ticketJpaEntity.getExpireDate(),
                ticketJpaEntity.isUsed(),
                ticketJpaEntity.getHowToUse(),
                ticketJpaEntity.getUsedDate()
        );
    }

    static TicketJpaEntity toJpaEntity(final Ticket ticket) {
        return new TicketJpaEntity(
                ticket.getMemberId(),
                ticket.getHowToAcquire()
        );
    }
}
