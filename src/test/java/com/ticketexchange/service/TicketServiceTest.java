package com.ticketexchange.service;

import static com.ticketexchange.fixture.MemberFixture.MEMBER_ID;
import static com.ticketexchange.fixture.MemberFixture.회원;
import static com.ticketexchange.fixture.MemberFixture.회원토큰;
import static com.ticketexchange.fixture.TicketFixture.HOW_TO_AQUIRE;
import static com.ticketexchange.fixture.TicketFixture.티켓;
import static com.ticketexchange.fixture.TicketFixture.티켓_발급_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.repository.MemberRepository;
import com.ticketexchange.repository.TicketRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TicketService.class)
class TicketServiceTest {

    @Autowired
    TicketService ticketService;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    TicketRepository ticketRepository;

    @Test
    void 티켓발급_테스트() {
        when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(회원()));

        final var result = ticketService.createTicket(회원토큰(), 티켓_발급_DTO());

        assertThat(result.getHowToAcquire()).isEqualTo(HOW_TO_AQUIRE);
    }

    @Test
    void 발급된_티켓_조회_테스트() {
        when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(회원()));
        when(ticketRepository.findAllByMemberId(MEMBER_ID)).thenReturn(List.of(티켓(회원())));
        final var result = ticketService.findAllTicketsByMember(회원토큰());

        assertThat(result).hasSize(1);
    }
}
