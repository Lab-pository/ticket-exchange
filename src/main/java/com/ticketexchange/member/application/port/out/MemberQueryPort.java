package com.ticketexchange.member.application.port.out;

import com.ticketexchange.member.domain.Member;

public interface MemberQueryPort {

    Member getByEmail(final String email);

    boolean existsByEmail(final String email);

    boolean existsByNickname(final String nickname);
}
