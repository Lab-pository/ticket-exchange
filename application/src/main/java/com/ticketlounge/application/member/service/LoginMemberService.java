package com.ticketlounge.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketlounge.application.member.port.in.LoginMemberUseCase;
import com.ticketlounge.application.member.port.out.MemberQueryPort;
import com.ticketlounge.domain.member.Member;
import com.ticketlounge.domain.member.Token;

@Service
@Transactional
public class LoginMemberService implements LoginMemberUseCase {

    private final MemberQueryPort memberQueryPort;

    public LoginMemberService(final MemberQueryPort memberQueryPort) {
        this.memberQueryPort = memberQueryPort;
    }

    @Override
    public Token login(final LoginCommand loginCommand) {
        final Member member = memberQueryPort.getByEmail(loginCommand.email());
        if (member.isSamePassword(loginCommand.password())) {
            return new Token(member.getId());
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}
