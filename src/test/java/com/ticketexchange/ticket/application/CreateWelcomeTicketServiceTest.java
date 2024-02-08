package com.ticketexchange.ticket.application;

import static com.ticketexchange.member.fixture.MemberFixture.MEMBER_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.member.domain.MemberCreateEvent;
import com.ticketexchange.ticket.application.port.out.SaveTicketPort;
import com.ticketexchange.ticket.application.service.CreateWelcomeTicketService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CreateWelcomeTicketService.class)
class CreateWelcomeTicketServiceTest {

    @Autowired
    CreateWelcomeTicketService createWelcomeTicketService;

    @MockBean
    SaveTicketPort saveTicketPort;

    @Test
    void 회원가입_이벤트_발생시_웰컴티켓을_생성한다() {
        var event = new MemberCreateEvent(MEMBER_ID);

        createWelcomeTicketService.createWelcomeTickets(event);

        verify(saveTicketPort).saveAll(any());
    }
}
