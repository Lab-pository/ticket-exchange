package com.ticketlounge.application.ticket;

import static com.ticketlounge.application.fixture.MemberFixture.MEMBER_ID;
import static com.ticketlounge.application.fixture.MemberFixture.회원;
import static com.ticketlounge.application.fixture.MemberFixture.회원토큰;
import static com.ticketlounge.application.fixture.TicketFixture.티켓;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketlounge.application.ticket.port.in.TicketsOfMemberQuery;
import com.ticketlounge.application.ticket.port.out.TicketQueryPort;
import com.ticketlounge.application.ticket.service.TicketsOfMemberQueryService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TicketsOfMemberQueryService.class)
class TicketsOfMemberQueryServiceTest {

    @Autowired
    TicketsOfMemberQuery ticketsOfMemberQuery;

    @MockBean
    TicketQueryPort ticketQueryPort;

    @Test
    void 발급된_티켓_조회_테스트() {
        when(ticketQueryPort.findAllByMemberId(MEMBER_ID)).thenReturn(List.of(티켓(회원())));

        final var result = ticketsOfMemberQuery.getTicketsOfMember(회원토큰().getId());

        assertThat(result).hasSize(1);
    }
}
