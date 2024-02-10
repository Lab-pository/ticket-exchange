package com.ticketlounge.application.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketlounge.application.fixture.MemberFixture;
import com.ticketlounge.application.member.port.in.CreateMemberUseCase;
import com.ticketlounge.application.member.port.out.SaveMemberPort;
import com.ticketlounge.application.member.service.CreateMemberService;
import com.ticketlounge.application.ticket.port.out.SaveTicketPort;
import com.ticketlounge.domain.member.MemberCreateEvent;

@RecordApplicationEvents
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CreateMemberService.class)
class CreateMemberServiceTest {

    @Autowired
    CreateMemberUseCase createMemberUseCase;

    @Autowired
    ApplicationEvents applicationEvents;

    @MockBean
    SaveMemberPort saveMemberPort;

    @MockBean
    SaveTicketPort saveTicketPort;

    @Test
    void 회원가입_테스트() {
        when(saveMemberPort.save(any())).thenReturn(MemberFixture.회원());

        final var result = createMemberUseCase.createMember(MemberFixture.회원가입_커맨드());

        assertThat(result.getId()).isEqualTo(MemberFixture.MEMBER_ID);
    }

    @Test
    void 회원가입_이벤트_발행_테스트() {
        when(saveMemberPort.save(any())).thenReturn(MemberFixture.회원());

        createMemberUseCase.createMember(MemberFixture.회원가입_커맨드());

        assertThat(applicationEvents.stream(MemberCreateEvent.class).count()).isEqualTo(1);
    }
}
