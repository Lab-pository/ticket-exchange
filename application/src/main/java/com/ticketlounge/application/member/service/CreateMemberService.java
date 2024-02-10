package com.ticketlounge.application.member.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketlounge.application.member.port.in.CreateMemberUseCase;
import com.ticketlounge.application.member.port.out.SaveMemberPort;
import com.ticketlounge.domain.member.Member;
import com.ticketlounge.domain.member.MemberCreateEvent;

@Service
@Transactional
public class CreateMemberService implements CreateMemberUseCase {

    private final SaveMemberPort saveMemberPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CreateMemberService(
            final SaveMemberPort saveMemberPort,
            final ApplicationEventPublisher applicationEventPublisher
    ) {
        this.saveMemberPort = saveMemberPort;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Member createMember(final CreateMemberCommand createMemberCommand) {
        final Member member = saveMemberPort.save(createMemberCommand.toEntity());
        applicationEventPublisher.publishEvent(new MemberCreateEvent(member.getId()));
        return member;
    }
}
