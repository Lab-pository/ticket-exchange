package com.ticketexchange.member.application.port.in;

public interface DuplicateMemberDetailsQuery {

    boolean isDuplicateNickname(final String nickname);

    boolean isDuplicateEmail(final String email);
}
