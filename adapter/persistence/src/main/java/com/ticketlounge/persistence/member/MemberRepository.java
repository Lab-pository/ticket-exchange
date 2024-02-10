package com.ticketlounge.persistence.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketlounge.persistence.member.entity.MemberJpaEntity;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {

    Optional<MemberJpaEntity> findByEmail(final String email);

    boolean existsByNickname(final String nickname);

    boolean existsByEmail(final String email);
}
