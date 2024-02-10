package com.ticketlounge.web.member.request;

import com.ticketlounge.application.member.port.in.LoginMemberUseCase.LoginCommand;

public record LoginRequest(String email, String password) {

    public LoginCommand toLoginCommand() {
        return new LoginCommand(email, password);
    }
}
