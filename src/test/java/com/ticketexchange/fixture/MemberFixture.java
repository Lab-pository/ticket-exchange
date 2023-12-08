package com.ticketexchange.fixture;

import java.time.Instant;

import org.springframework.test.util.ReflectionTestUtils;

import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.controller.dto.LoginRequest;
import com.ticketexchange.controller.dto.MemberRequest;
import com.ticketexchange.domain.Member;
import com.ticketexchange.service.dto.CreateMemberDto;
import com.ticketexchange.service.dto.LoginDto;

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

    public static CreateMemberDto 회원가입_DTO() {
        return new CreateMemberDto(EMAIL, NICKNAME, PASSWORD);
    }

    public static LoginDto 로그인_DTO() {
        return 로그인_DTO(EMAIL, PASSWORD);
    }

    public static LoginDto 로그인_DTO(final String email, final String password) {
        return new LoginDto(email, password);
    }

    public static MemberToken 회원토큰() {
        return new MemberToken(MEMBER_ID, Instant.now().toEpochMilli() + TOKEN_EXPIRE);
    }

    public static Member 회원() {
        final Member member = new Member(EMAIL, NICKNAME, PASSWORD);
        ReflectionTestUtils.setField(member, "id", MEMBER_ID);
        return member;
    }
}
