package com.ticketexchange.member.application;

import static com.ticketexchange.member.fixture.MemberFixture.EMAIL;
import static com.ticketexchange.member.fixture.MemberFixture.로그인_커맨드;
import static com.ticketexchange.member.fixture.MemberFixture.회원;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.member.application.port.in.LoginMemberUseCase;
import com.ticketexchange.member.application.port.out.MemberQueryPort;
import com.ticketexchange.member.application.service.LoginMemberService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LoginMemberService.class)
class LoginMemberServiceTest {

    @Autowired
    LoginMemberUseCase loginMemberUseCase;

    @MockBean
    MemberQueryPort memberQueryPort;

    @Test
    void 로그인_테스트() {
        when(memberQueryPort.getByEmail(EMAIL)).thenReturn(회원());

        final var result = loginMemberUseCase.login(로그인_커맨드());

        assertThat(result).isNotBlank();
    }

    @Test
    void 로그인_실패_테스트() {
        final var 잘못된_로그인_커맨드 = 로그인_커맨드(EMAIL, "wrong_password");
        when(memberQueryPort.getByEmail(EMAIL)).thenReturn(회원());

        assertThatThrownBy(() -> loginMemberUseCase.login(잘못된_로그인_커맨드))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
