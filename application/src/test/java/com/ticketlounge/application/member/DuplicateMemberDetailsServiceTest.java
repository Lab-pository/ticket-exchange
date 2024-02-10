package com.ticketlounge.application.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketlounge.application.fixture.MemberFixture;
import com.ticketlounge.application.member.port.in.DuplicateMemberDetailsQuery;
import com.ticketlounge.application.member.port.out.MemberQueryPort;
import com.ticketlounge.application.member.service.DuplicateMemberDetailsService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DuplicateMemberDetailsService.class)
class DuplicateMemberDetailsServiceTest {

    @Autowired
    DuplicateMemberDetailsQuery duplicateMemberDetailsQuery;

    @MockBean
    MemberQueryPort memberQueryPort;

    @Test
    void 이메일_중복아닌_경우_테스트() {
        when(memberQueryPort.existsByEmail(MemberFixture.EMAIL)).thenReturn(false);

        assertThat(duplicateMemberDetailsQuery.isDuplicateEmail(MemberFixture.EMAIL)).isFalse();
    }

    @Test
    void 닉네임_중복아닌_경우_테스트() {
        when(memberQueryPort.existsByNickname(MemberFixture.NICKNAME)).thenReturn(false);

        assertThat(duplicateMemberDetailsQuery.isDuplicateNickname(MemberFixture.NICKNAME)).isFalse();
    }

    @Test
    void 이메일_중복인_경우_테스트() {
        when(memberQueryPort.existsByEmail(MemberFixture.EMAIL)).thenReturn(true);

        assertThat(duplicateMemberDetailsQuery.isDuplicateEmail(MemberFixture.EMAIL)).isTrue();
    }

    @Test
    void 닉네임_중복인_경우_테스트() {
        when(memberQueryPort.existsByNickname(MemberFixture.NICKNAME)).thenReturn(true);

        assertThat(duplicateMemberDetailsQuery.isDuplicateNickname(MemberFixture.NICKNAME)).isTrue();
    }
}
