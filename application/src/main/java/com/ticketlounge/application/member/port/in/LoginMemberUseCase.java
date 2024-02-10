package com.ticketlounge.application.member.port.in;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.member.Token;

@Component
public interface LoginMemberUseCase {

    Token login(final LoginCommand loginCommand);

    record LoginCommand(String email, String password) {

        public LoginCommand {
            if (email == null) {
                throw new IllegalArgumentException();
            }
            if (password == null) {
                throw new IllegalArgumentException();
            }
        }
    }
}
