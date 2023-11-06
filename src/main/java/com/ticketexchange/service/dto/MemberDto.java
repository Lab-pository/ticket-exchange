package com.ticketexchange.service.dto;

import com.ticketexchange.domain.Member;

public class MemberDto {

    private Long memberId;
    private String email;
    private String nickname;

    protected MemberDto() {
    }

    public MemberDto(Long memberId, String email, String nickname) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
    }

    public static MemberDto of(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname());
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
