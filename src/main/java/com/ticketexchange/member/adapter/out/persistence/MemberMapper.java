package com.ticketexchange.member.adapter.out.persistence;

import com.ticketexchange.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.ticketexchange.member.domain.Member;

public final class MemberMapper {

    private MemberMapper() {
    }

    static Member toDomain(final MemberJpaEntity memberJpaEntity) {
        return new Member(
                memberJpaEntity.getId(),
                memberJpaEntity.getEmail(),
                memberJpaEntity.getNickname(),
                memberJpaEntity.getPassword()
        );
    }

    static MemberJpaEntity toJpaEntity(final Member member) {
         return new MemberJpaEntity(
                 member.getId(),
                 member.getEmail(),
                 member.getNickname(),
                 member.getPassword()
         );
    }
}
