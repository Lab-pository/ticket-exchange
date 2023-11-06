package com.ticketexchange.controller.dto;

import com.ticketexchange.service.dto.MemberDto;

public class MemberResponse {

    private Long memberId;
    private String email;
    private String nickname;

    protected MemberResponse() {
    }

    public MemberResponse(Long memberId, String email, String nickname) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
    }

    public static MemberResponse of(MemberDto memberDto) {
        return new MemberResponse(memberDto.getMemberId(), memberDto.getEmail(), memberDto.getNickname());
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
