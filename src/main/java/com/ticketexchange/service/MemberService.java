package com.ticketexchange.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

	public MemberService(MemberRepository memberRepository, TicketRepository ticketRepository) {
		this.memberRepository = memberRepository;
		this.ticketRepository = ticketRepository;
	}

	public MemberDto createMember(CreateMemberDto createMemberDto) {
		Member member = memberRepository.save(createMemberDto.toEntity());
		createWelcomeTickets(member);
		return MemberDto.of(member);
	}

	private void createWelcomeTickets(Member member) {
		List<Ticket> welcomeTickets = new ArrayList<>();
		for (int i = 0; i < WELCOME_TICKET_COUNT; i++) {
			welcomeTickets.add(new Ticket(member, WELCOME_TICKET_MESSAGE));
		}
		ticketRepository.saveAll(welcomeTickets);
	}

	public TokenDto login(LoginDto loginDto) {
		Member member = memberRepository.findByEmail(loginDto.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		if (member.getPassword().equals(loginDto.getPassword())) {
			return TokenDto.of(member.getId(), getExpireTime());
		}
		throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
	}

	private long getExpireTime() {
		return Instant.now().toEpochMilli() + TOKEN_EXPIRE;
	}
}
