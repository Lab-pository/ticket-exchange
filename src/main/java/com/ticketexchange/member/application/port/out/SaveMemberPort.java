package com.ticketexchange.member.application.port.out;

import com.ticketexchange.member.domain.Member;

public interface SaveMemberPort {

    Member save(final Member member);
}
