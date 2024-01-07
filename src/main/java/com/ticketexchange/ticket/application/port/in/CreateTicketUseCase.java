package com.ticketexchange.ticket.application.port.in;

import java.time.LocalDate;

import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.ticket.domain.Ticket;

public interface CreateTicketUseCase {

    Ticket createTicket(final MemberToken token, final CreateTicketCommand createTicketCommand);

    record CreateTicketCommand(String howToAcquire, int count) {

        public CreateTicketCommand {
            if (count <= 0) {
                throw new IllegalArgumentException();
            }
            if (howToAcquire == null) {
                throw new IllegalArgumentException();
            }
        }

        public Ticket toDomain(Long memberId, LocalDate acquireDate) {
            return new Ticket(null, memberId, howToAcquire, acquireDate);
        }
    }
}
