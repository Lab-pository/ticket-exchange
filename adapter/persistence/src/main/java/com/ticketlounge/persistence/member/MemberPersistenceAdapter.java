package com.ticketlounge.persistence.member;

import org.springframework.stereotype.Repository;

import com.ticketlounge.persistence.member.entity.MemberJpaEntity;
import com.ticketlounge.application.member.port.out.MemberQueryPort;
import com.ticketlounge.application.member.port.out.SaveMemberPort;
import com.ticketlounge.domain.member.Member;

@Repository
public class MemberPersistenceAdapter implements MemberQueryPort, SaveMemberPort {

    private final MemberRepository memberRepository;

    public MemberPersistenceAdapter(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getByEmail(final String email) {
        return MemberMapper.toDomain(memberRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public boolean existsByEmail(final String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(final String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public Member save(final Member member) {
        final MemberJpaEntity memberJpaEntity = memberRepository.save(MemberMapper.toJpaEntity(member));
        return MemberMapper.toDomain(memberJpaEntity);
    }
}
