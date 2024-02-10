package com.ticketlounge.ticket.fixture;

import java.time.LocalDate;

import com.ticketlounge.web.ticket.request.TicketRequest;

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

}
