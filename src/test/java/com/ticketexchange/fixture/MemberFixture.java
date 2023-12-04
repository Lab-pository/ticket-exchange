package com.ticketexchange.fixture;

import java.time.Instant;
import java.util.Base64;

import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.controller.dto.LoginRequest;
import com.ticketexchange.controller.dto.MemberRequest;
import com.ticketexchange.utils.JsonUtils;

public final class MemberFixture {

    public static final Long MEMBER_ID = 1L;
    public static final Long TOKEN_EXPIRE = 1000L * 60 * 60 * 24 * 7;
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

    public static String 회원토큰() {
        String token = JsonUtils.toJson(new MemberToken(MEMBER_ID, Instant.now().toEpochMilli() + TOKEN_EXPIRE));
        return Base64.getEncoder().encodeToString(token.getBytes());
    }
}
