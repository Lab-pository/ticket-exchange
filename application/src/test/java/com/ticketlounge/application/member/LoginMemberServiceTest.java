package com.ticketlounge.application.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketlounge.application.fixture.MemberFixture;
import com.ticketlounge.application.member.port.in.LoginMemberUseCase;
import com.ticketlounge.application.member.port.out.MemberQueryPort;
import com.ticketlounge.application.member.service.LoginMemberService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LoginMemberService.class)
class LoginMemberServiceTest {

    @Autowired
    LoginMemberUseCase loginMemberUseCase;

    @MockBean
    MemberQueryPort memberQueryPort;

    @Test
    void 로그인_테스트() {
        when(memberQueryPort.getByEmail(MemberFixture.EMAIL)).thenReturn(MemberFixture.회원());

        final var result = loginMemberUseCase.login(MemberFixture.로그인_커맨드());

        assertThat(result).isNotNull();
    }

    @Test
    void 로그인_실패_테스트() {
        final var 잘못된_로그인_커맨드 = MemberFixture.로그인_커맨드(MemberFixture.EMAIL, "wrong_password");
        when(memberQueryPort.getByEmail(MemberFixture.EMAIL)).thenReturn(MemberFixture.회원());

        assertThatThrownBy(() -> loginMemberUseCase.login(잘못된_로그인_커맨드))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
