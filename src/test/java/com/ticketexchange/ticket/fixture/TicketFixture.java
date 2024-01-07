package com.ticketexchange.ticket.fixture;

import java.time.LocalDate;

import org.springframework.test.util.ReflectionTestUtils;

import com.ticketexchange.member.domain.Member;
import com.ticketexchange.ticket.adapter.in.web.request.TicketRequest;
import com.ticketexchange.ticket.application.port.in.CreateTicketUseCase.CreateTicketCommand;
import com.ticketexchange.ticket.domain.Ticket;

public final class TicketFixture {

    public static final Long TICKET_ID = 1L;
    public static final String HOW_TO_ACQUIRE = "회원가입 기념 웰컴 티켓";
    public static final LocalDate ACQUIRE_DATE = LocalDate.of(2023, 12, 1);
    public static final int COUNT = 10;

    private TicketFixture() {
    }

    public static TicketRequest 티켓_발급_요청() {
        return 티켓_발급_요청(HOW_TO_ACQUIRE, COUNT);
    }

    public static TicketRequest 티켓_발급_요청(final String howToAcquire, final int count) {
        return new TicketRequest(howToAcquire, count);
    }

    public static CreateTicketCommand 티켓_발급_커맨드() {
        return 티켓_발급_커맨드(HOW_TO_ACQUIRE, COUNT);
    }

    public static CreateTicketCommand 티켓_발급_커맨드(final String howToAcquire, final int count) {
        return new CreateTicketCommand(howToAcquire, count);
    }

    public static Ticket 티켓(final Member 회원) {
        final Ticket ticket = new Ticket(TICKET_ID, 회원.getId(), HOW_TO_ACQUIRE, ACQUIRE_DATE);
        ReflectionTestUtils.setField(ticket, "id", TICKET_ID);
        return ticket;
    }
}
