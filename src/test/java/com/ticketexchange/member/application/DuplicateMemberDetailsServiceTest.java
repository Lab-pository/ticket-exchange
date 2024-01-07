package com.ticketexchange.member.application;

import static com.ticketexchange.member.fixture.MemberFixture.EMAIL;
import static com.ticketexchange.member.fixture.MemberFixture.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketexchange.member.application.port.in.DuplicateMemberDetailsQuery;
import com.ticketexchange.member.application.port.out.MemberQueryPort;
import com.ticketexchange.member.application.service.DuplicateMemberDetailsService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DuplicateMemberDetailsService.class)
class DuplicateMemberDetailsServiceTest {

    @Autowired
    DuplicateMemberDetailsQuery duplicateMemberDetailsQuery;

    @MockBean
    MemberQueryPort memberQueryPort;

    @Test
    void 이메일_중복아닌_경우_테스트() {
        when(memberQueryPort.existsByEmail(EMAIL)).thenReturn(false);

        assertThat(duplicateMemberDetailsQuery.isDuplicateEmail(EMAIL)).isFalse();
    }

    @Test
    void 닉네임_중복아닌_경우_테스트() {
        when(memberQueryPort.existsByNickname(NICKNAME)).thenReturn(false);

        assertThat(duplicateMemberDetailsQuery.isDuplicateNickname(NICKNAME)).isFalse();
    }

    @Test
    void 이메일_중복인_경우_테스트() {
        when(memberQueryPort.existsByEmail(EMAIL)).thenReturn(true);

        assertThat(duplicateMemberDetailsQuery.isDuplicateEmail(EMAIL)).isTrue();
    }

    @Test
    void 닉네임_중복인_경우_테스트() {
        when(memberQueryPort.existsByNickname(NICKNAME)).thenReturn(true);

        assertThat(duplicateMemberDetailsQuery.isDuplicateNickname(NICKNAME)).isTrue();
    }
}
