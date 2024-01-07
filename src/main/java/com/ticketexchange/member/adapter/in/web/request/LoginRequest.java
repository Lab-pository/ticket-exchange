package com.ticketexchange.member.adapter.in.web.request;

import com.ticketexchange.member.application.port.in.LoginMemberUseCase.LoginCommand;

public record LoginRequest(String email, String password) {

    public LoginCommand toLoginCommand() {
        return new LoginCommand(email, password);
    }
}
