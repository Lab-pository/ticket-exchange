package com.ticketexchange.fixture;

import java.time.LocalDate;
import java.util.List;

import com.ticketexchange.controller.dto.TicketDetailsResponse;
import com.ticketexchange.controller.dto.TicketDetailsResponses;
import com.ticketexchange.controller.dto.TicketRequest;
import com.ticketexchange.controller.dto.TicketResponse;

public final class TicketFixture {

    public static final Long TICKET_ID = 1L;
    public static final Long EXPIRED_TICKET_ID = 2L;
    public static final Long USED_TICKET_ID = 3L;
    public static final String HOW_TO_AQUIRE = "회원가입 기념 웰컴 티켓";
    public static final String HOW_TO_USE = "빨간 포션";
    public static final LocalDate ACQUIRE_DATE = LocalDate.of(2023, 12, 1);
    public static final LocalDate USED_DATE = LocalDate.of(2023, 12, 20);
    public static final LocalDate EXPIRED_DATE = ACQUIRE_DATE.plusDays(180);
    public static final int COUNT = 10;

    private TicketFixture() {
    }

    public static TicketRequest 티켓_발급_요청() {
        return new TicketRequest(HOW_TO_AQUIRE, COUNT);
    }

    public static TicketRequest 티켓_발급_요청(final String howToAquire, final int count) {
        return new TicketRequest(howToAquire, count);
    }

    public static TicketResponse 티켓_발급_응답() {
        return new TicketResponse(HOW_TO_AQUIRE, ACQUIRE_DATE, EXPIRED_DATE);
    }

    public static TicketDetailsResponse 유효한_티켓_응답() {
        return new TicketDetailsResponse(TICKET_ID, HOW_TO_AQUIRE, ACQUIRE_DATE, EXPIRED_DATE, false, null, null);
    }

    public static TicketDetailsResponse 만료된_티켓_응답() {
        return new TicketDetailsResponse(EXPIRED_TICKET_ID, HOW_TO_AQUIRE, ACQUIRE_DATE.minusDays(180),
                EXPIRED_DATE.minusDays(180), false, null, null
        );
    }

    public static TicketDetailsResponse 사용한_티켓_응답() {
        return new TicketDetailsResponse(USED_TICKET_ID, HOW_TO_AQUIRE, ACQUIRE_DATE, EXPIRED_DATE, true, HOW_TO_USE,
                USED_DATE
        );
    }

    public static TicketDetailsResponses 전체_티켓_응답() {
        return new TicketDetailsResponses(3, 1, 1, 1, List.of(
                유효한_티켓_응답(), 만료된_티켓_응답(), 사용한_티켓_응답()
        ));
    }

}
