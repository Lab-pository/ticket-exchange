package com.ticketexchange.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.member.application.port.in.DuplicateMemberDetailsQuery;
import com.ticketexchange.member.application.port.out.MemberQueryPort;

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
