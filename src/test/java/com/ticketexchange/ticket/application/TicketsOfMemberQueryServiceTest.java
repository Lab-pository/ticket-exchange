package com.ticketexchange.ticket.application;

import static com.ticketexchange.member.fixture.MemberFixture.MEMBER_ID;
import static com.ticketexchange.member.fixture.MemberFixture.회원;
import static com.ticketexchange.member.fixture.MemberFixture.회원토큰;
import static com.ticketexchange.ticket.fixture.TicketFixture.티켓;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.ticket.application.port.in.TicketsOfMemberQuery;
import com.ticketexchange.ticket.application.port.out.TicketQueryPort;
import com.ticketexchange.ticket.application.service.TicketsOfMemberQueryService;

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
