package com.ticketlounge.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketlounge.application.member.port.in.DuplicateMemberDetailsQuery;
import com.ticketlounge.application.member.port.out.MemberQueryPort;

@Service
@Transactional(readOnly = true)
public class DuplicateMemberDetailsService implements DuplicateMemberDetailsQuery {

    private final MemberQueryPort memberQueryPort;

    public DuplicateMemberDetailsService(final MemberQueryPort memberQueryPort) {
        this.memberQueryPort = memberQueryPort;

    }

    @Override
    public boolean isDuplicateNickname(final String nickname) {
        return memberQueryPort.existsByNickname(nickname);
    }

    @Override
    public boolean isDuplicateEmail(final String email) {
        return memberQueryPort.existsByEmail(email);
    }
}
