package com.ticketexchange.member.application.port.in;

import com.ticketexchange.member.domain.Member;

public interface CreateMemberUseCase {

    Member createMember(final CreateMemberCommand createMemberCommand);

    record CreateMemberCommand(String email, String nickname, String password) {

        public CreateMemberCommand {
            if (email == null) {
                throw new IllegalArgumentException();
            }
            if (nickname == null) {
                throw new IllegalArgumentException();
            }
            if (password == null) {
                throw new IllegalArgumentException();
            }
        }

        public Member toEntity() {
            return new Member(null, email, nickname, password);
        }
    }
}
