package com.ticketlounge.persistence.member;

import com.ticketlounge.persistence.member.entity.MemberJpaEntity;
import com.ticketlounge.domain.member.Member;

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
