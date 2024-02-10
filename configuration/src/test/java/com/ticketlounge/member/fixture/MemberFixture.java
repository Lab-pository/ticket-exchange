package com.ticketlounge.member.fixture;

import com.ticketlounge.web.member.request.LoginRequest;
import com.ticketlounge.web.member.request.MemberRequest;

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

}
