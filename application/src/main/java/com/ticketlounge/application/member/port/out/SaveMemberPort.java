package com.ticketlounge.application.member.port.out;

import com.ticketlounge.domain.member.Member;

public interface SaveMemberPort {

    Member save(final Member member);
}
