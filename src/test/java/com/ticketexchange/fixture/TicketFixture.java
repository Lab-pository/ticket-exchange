package com.ticketexchange.fixture;

import org.springframework.test.util.ReflectionTestUtils;

import com.ticketexchange.controller.dto.TicketRequest;
import com.ticketexchange.domain.Member;
import com.ticketexchange.domain.Ticket;
import com.ticketexchange.service.dto.CreateTicketDto;

public final class TicketFixture {

    public static final Long TICKET_ID = 1L;
    public static final String HOW_TO_AQUIRE = "회원가입 기념 웰컴 티켓";
    public static final int COUNT = 10;

    private TicketFixture() {
    }

    public static TicketRequest 티켓_발급_요청() {
        return 티켓_발급_요청(HOW_TO_AQUIRE, COUNT);
    }

    public static TicketRequest 티켓_발급_요청(final String howToAquire, final int count) {
        return new TicketRequest(howToAquire, count);
    }

    public static CreateTicketDto 티켓_발급_DTO() {
        return 티켓_발급_DTO(HOW_TO_AQUIRE, COUNT);
    }

    public static CreateTicketDto 티켓_발급_DTO(final String howToAquire, final int count) {
        return new CreateTicketDto(howToAquire, count);
    }

    public static Ticket 티켓(final Member 회원) {
        final Ticket ticket = new Ticket(회원, HOW_TO_AQUIRE);
        ReflectionTestUtils.setField(ticket, "id", TICKET_ID);
        return ticket;
    }
}
