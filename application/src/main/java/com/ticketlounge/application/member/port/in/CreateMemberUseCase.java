package com.ticketlounge.application.member.port.in;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.member.Member;

@Component
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
