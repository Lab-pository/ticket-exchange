package com.ticketexchange.member.application.port.in;

public interface LoginMemberUseCase {

    String login(final LoginCommand loginCommand);

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
