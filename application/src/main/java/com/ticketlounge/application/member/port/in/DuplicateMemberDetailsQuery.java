package com.ticketlounge.application.member.port.in;

import org.springframework.stereotype.Component;

@Component
public interface DuplicateMemberDetailsQuery {

    boolean isDuplicateNickname(final String nickname);

    boolean isDuplicateEmail(final String email);
}
