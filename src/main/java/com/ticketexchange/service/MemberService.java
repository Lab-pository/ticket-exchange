package com.ticketexchange.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.domain.Member;
import com.ticketexchange.domain.Ticket;
import com.ticketexchange.repository.MemberRepository;
import com.ticketexchange.repository.TicketRepository;
import com.ticketexchange.service.dto.CreateMemberDto;
import com.ticketexchange.service.dto.LoginDto;
import com.ticketexchange.service.dto.MemberDto;
import com.ticketexchange.service.dto.TokenDto;

@Service
@Transactional
public class MemberService {

    private static final Long TOKEN_EXPIRE = 1000L * 60 * 60 * 24 * 7;
    private static final int WELCOME_TICKET_COUNT = 200;
    private static final String WELCOME_TICKET_MESSAGE = "회원가입 기념 웰컴 티켓";

    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;

    public MemberService(final MemberRepository memberRepository, final TicketRepository ticketRepository) {
        this.memberRepository = memberRepository;
        this.ticketRepository = ticketRepository;
    }

    public MemberDto createMember(final CreateMemberDto createMemberDto) {
        final Member member = memberRepository.save(createMemberDto.toEntity());
        createWelcomeTickets(member);
        return MemberDto.from(member);
    }

    private void createWelcomeTickets(final Member member) {
        final List<Ticket> welcomeTickets = IntStream.range(0, WELCOME_TICKET_COUNT)
                .mapToObj(i -> new Ticket(member, WELCOME_TICKET_MESSAGE))
                .toList();
        ticketRepository.saveAll(welcomeTickets);
    }

    public TokenDto login(final LoginDto loginDto) {
        final Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        if (member.getPassword().equals(loginDto.getPassword())) {
            return TokenDto.of(member.getId(), getExpireTime());
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    private long getExpireTime() {
        return Instant.now().toEpochMilli() + TOKEN_EXPIRE;
    }

    @Transactional(readOnly = true)
    public boolean isDuplicateEmail(final String email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean isDuplicateNickname(final String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}
