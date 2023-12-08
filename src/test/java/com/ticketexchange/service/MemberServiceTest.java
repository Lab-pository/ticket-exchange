package com.ticketexchange.service;


import static com.ticketexchange.fixture.MemberFixture.EMAIL;
import static com.ticketexchange.fixture.MemberFixture.MEMBER_ID;
import static com.ticketexchange.fixture.MemberFixture.NICKNAME;
import static com.ticketexchange.fixture.MemberFixture.로그인_DTO;
import static com.ticketexchange.fixture.MemberFixture.회원;
import static com.ticketexchange.fixture.MemberFixture.회원가입_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.repository.MemberRepository;
import com.ticketexchange.repository.TicketRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MemberService.class)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    TicketRepository ticketRepository;

    @Test
    void 회원가입_테스트() {
        when(memberRepository.save(any())).thenReturn(회원());

        final var result = memberService.createMember(회원가입_DTO());

        assertThat(result.getMemberId()).isEqualTo(MEMBER_ID);
    }

    @Test
    void 로그인_테스트() {
        when(memberRepository.findByEmail(EMAIL)).thenReturn(Optional.of(회원()));

        final var result = memberService.login(로그인_DTO());

        assertThat(result.getId()).isEqualTo(MEMBER_ID);
    }

    @Test
    void 로그인_실패_테스트() {
        final var 잘못된_로그인_DTO = 로그인_DTO(EMAIL, "wrong_password");
        when(memberRepository.findByEmail(EMAIL)).thenReturn(Optional.of(회원()));

        assertThatThrownBy(() -> memberService.login(잘못된_로그인_DTO))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이메일_중복아닌_경우_테스트() {
        when(memberRepository.existsByEmail(EMAIL)).thenReturn(false);

        assertThat(memberService.isDuplicateEmail(EMAIL)).isFalse();
    }

    @Test
    void 닉네임_중복아닌_경우_테스트() {
        when(memberRepository.existsByNickname(NICKNAME)).thenReturn(false);

        assertThat(memberService.isDuplicateNickname(NICKNAME)).isFalse();
    }
    @Test
    void 이메일_중복인_경우_테스트() {
        when(memberRepository.existsByEmail(EMAIL)).thenReturn(true);

        assertThat(memberService.isDuplicateEmail(EMAIL)).isTrue();
    }

    @Test
    void 닉네임_중복인_경우_테스트() {
        when(memberRepository.existsByNickname(NICKNAME)).thenReturn(true);

        assertThat(memberService.isDuplicateNickname(NICKNAME)).isTrue();
    }
}
