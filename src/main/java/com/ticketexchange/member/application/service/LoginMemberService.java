package com.ticketexchange.member.application.service;

import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.member.application.port.in.LoginMemberUseCase;
import com.ticketexchange.member.application.port.out.MemberQueryPort;
import com.ticketexchange.member.domain.Member;
import com.ticketexchange.member.domain.Token;
import com.ticketexchange.utils.JsonUtils;

@Service
@Transactional
public class LoginMemberService implements LoginMemberUseCase {

    private final MemberQueryPort memberQueryPort;

    public LoginMemberService(final MemberQueryPort memberQueryPort) {
        this.memberQueryPort = memberQueryPort;
    }

    @Override
    public String login(final LoginCommand loginCommand) {
        final Member member = memberQueryPort.getByEmail(loginCommand.email());
        if (member.isSamePassword(loginCommand.password())) {
            final String tokenJson = JsonUtils.toJson(new Token(member.getId()));
            return Base64.getEncoder().encodeToString(tokenJson.getBytes());
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}
