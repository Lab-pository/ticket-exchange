package com.ticketexchange.member.adapter.in.web.request;

import com.ticketexchange.member.application.port.in.CreateMemberUseCase.CreateMemberCommand;

public record MemberRequest(String email, String nickname, String password) {

    public CreateMemberCommand toCreateMemberCommand() {
        return new CreateMemberCommand(email, nickname, password);
    }
}
