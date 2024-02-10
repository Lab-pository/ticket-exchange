package com.ticketlounge.web.member.request;

import com.ticketlounge.application.member.port.in.CreateMemberUseCase.CreateMemberCommand;

public record MemberRequest(String email, String nickname, String password) {

    public CreateMemberCommand toCreateMemberCommand() {
        return new CreateMemberCommand(email, nickname, password);
    }
}
