package com.ticketexchange.member.fixture;

import java.time.Instant;

import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.member.adapter.in.web.request.LoginRequest;
import com.ticketexchange.member.adapter.in.web.request.MemberRequest;
import com.ticketexchange.member.application.port.in.CreateMemberUseCase.CreateMemberCommand;
import com.ticketexchange.member.application.port.in.LoginMemberUseCase.LoginCommand;
import com.ticketexchange.member.domain.Member;

public final class MemberFixture {

    public static final Long TOKEN_EXPIRE = 1000L * 60 * 60 * 24 * 7;
    public static final Long MEMBER_ID = 1L;
    public static final String EMAIL = "protoseo@naver.com";
    public static final String NICKNAME = "protoseo";
    public static final String PASSWORD = "password";

    private MemberFixture() {
    }

    public static LoginRequest 로그인_요청() {
        return new LoginRequest(EMAIL, PASSWORD);
    }

    public static LoginRequest 로그인_요청(final String email, final String password) {
        return new LoginRequest(email, password);
    }

    public static MemberRequest 회원가입_요청() {
        return new MemberRequest(EMAIL, NICKNAME, PASSWORD);
    }

    public static MemberRequest 회원가입_요청(final String email, final String nickname, final String password) {
        return new MemberRequest(email, nickname, password);
    }

    public static CreateMemberCommand 회원가입_커맨드() {
        return new CreateMemberCommand(EMAIL, NICKNAME, PASSWORD);
    }

    public static LoginCommand 로그인_커맨드() {
        return 로그인_커맨드(EMAIL, PASSWORD);
    }

    public static LoginCommand 로그인_커맨드(final String email, final String password) {
        return new LoginCommand(email, password);
    }

    public static MemberToken 회원토큰() {
        return new MemberToken(MEMBER_ID, Instant.now().toEpochMilli() + TOKEN_EXPIRE);
    }

    public static Member 회원() {
        return new Member(MEMBER_ID, EMAIL, NICKNAME, PASSWORD);
    }
}
