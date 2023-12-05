package com.ticketexchange.fixture;

import com.ticketexchange.controller.dto.LoginRequest;
import com.ticketexchange.controller.dto.MemberRequest;

public final class MemberFixture {

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
