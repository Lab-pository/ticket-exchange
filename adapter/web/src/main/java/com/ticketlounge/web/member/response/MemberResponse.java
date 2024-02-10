package com.ticketlounge.web.member.response;

import com.ticketlounge.domain.member.Member;

public record MemberResponse(Long memberId, String email, String nickname) {

    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getNickname());
    }
}
