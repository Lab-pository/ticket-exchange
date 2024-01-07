package com.ticketexchange.member.application;

import static com.ticketexchange.member.fixture.MemberFixture.MEMBER_ID;
import static com.ticketexchange.member.fixture.MemberFixture.회원;
import static com.ticketexchange.member.fixture.MemberFixture.회원가입_커맨드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.member.application.port.in.CreateMemberUseCase;
import com.ticketexchange.member.application.port.out.SaveMemberPort;
import com.ticketexchange.member.application.service.CreateMemberService;
import com.ticketexchange.ticket.application.port.out.SaveTicketPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CreateMemberService.class)
class CreateMemberServiceTest {

    @Autowired
    CreateMemberUseCase createMemberUseCase;

    @MockBean
    SaveMemberPort saveMemberPort;

    @MockBean
    SaveTicketPort saveTicketPort;

    @Test
    void 회원가입_테스트() {
        when(saveMemberPort.save(any())).thenReturn(회원());

        final var result = createMemberUseCase.createMember(회원가입_커맨드());

        assertThat(result.getId()).isEqualTo(MEMBER_ID);
    }
}
