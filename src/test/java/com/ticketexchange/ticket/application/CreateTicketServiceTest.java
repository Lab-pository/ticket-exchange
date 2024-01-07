package com.ticketexchange.ticket.application;

import static com.ticketexchange.member.fixture.MemberFixture.회원토큰;
import static com.ticketexchange.ticket.fixture.TicketFixture.HOW_TO_ACQUIRE;
import static com.ticketexchange.ticket.fixture.TicketFixture.티켓_발급_커맨드;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.ticket.application.port.in.CreateTicketUseCase;
import com.ticketexchange.ticket.application.port.out.SaveTicketPort;
import com.ticketexchange.ticket.application.service.CreateTicketService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CreateTicketService.class)
class CreateTicketServiceTest {

    @Autowired
    CreateTicketUseCase createTicketUseCase;

    @MockBean
    SaveTicketPort saveTicketPort;

    @Test
    void 티켓발급_테스트() {
        final var result = createTicketUseCase.createTicket(회원토큰(), 티켓_발급_커맨드());

        assertThat(result.getHowToAcquire()).isEqualTo(HOW_TO_ACQUIRE);
    }
}
