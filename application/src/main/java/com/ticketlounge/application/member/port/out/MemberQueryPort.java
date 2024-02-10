package com.ticketlounge.application.member.port.out;

import com.ticketlounge.domain.member.Member;

public interface MemberQueryPort {

    Member getByEmail(final String email);

    boolean existsByEmail(final String email);

    boolean existsByNickname(final String nickname);
}
