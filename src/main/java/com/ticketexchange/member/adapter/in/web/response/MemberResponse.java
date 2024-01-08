package com.ticketexchange.member.adapter.in.web.response;

import com.ticketexchange.member.domain.Member;

public record MemberResponse(Long memberId, String email, String nickname) {

    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getNickname());
    }
}