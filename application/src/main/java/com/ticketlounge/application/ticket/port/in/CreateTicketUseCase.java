package com.ticketlounge.application.ticket.port.in;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.auth.MemberToken;
import com.ticketlounge.domain.ticket.Ticket;

@Component
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
