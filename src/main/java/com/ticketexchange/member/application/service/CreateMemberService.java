package com.ticketexchange.member.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.member.application.port.in.CreateMemberUseCase;
import com.ticketexchange.member.application.port.out.SaveMemberPort;
import com.ticketexchange.member.domain.Member;
import com.ticketexchange.member.domain.MemberCreateEvent;

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
