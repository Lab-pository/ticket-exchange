package com.ticketlounge.web.member;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketlounge.application.member.port.in.CreateMemberUseCase;
import com.ticketlounge.application.member.port.in.DuplicateMemberDetailsQuery;
import com.ticketlounge.application.member.port.in.LoginMemberUseCase;
import com.ticketlounge.web.common.ApiResult;
import com.ticketlounge.web.member.request.LoginRequest;
import com.ticketlounge.web.member.request.MemberRequest;
import com.ticketlounge.web.member.response.LoginResponse;
import com.ticketlounge.web.member.response.MemberResponse;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final CreateMemberUseCase createMemberUseCase;
    private final LoginMemberUseCase loginMemberUseCase;
    private final DuplicateMemberDetailsQuery duplicateMemberDetailsQuery;

    public MemberController(
            final CreateMemberUseCase createMemberUseCase,
            final LoginMemberUseCase loginMemberUseCase,
            final DuplicateMemberDetailsQuery duplicateMemberDetailsQuery
    ) {
        this.createMemberUseCase = createMemberUseCase;
        this.loginMemberUseCase = loginMemberUseCase;
        this.duplicateMemberDetailsQuery = duplicateMemberDetailsQuery;
    }

    @PostMapping("/members")
    public ResponseEntity<ApiResult<MemberResponse>> createMember(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = MemberResponse.from(
                createMemberUseCase.createMember(memberRequest.toCreateMemberCommand()));

        return ResponseEntity.created(URI.create("/members/" + memberResponse.memberId()))
                .body(ApiResult.succeed(memberResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResult<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = LoginResponse.from(loginMemberUseCase.login(loginRequest.toLoginCommand()));
        return ResponseEntity.ok(ApiResult.succeed(loginResponse));
    }

    @GetMapping("/members/email")
    public ResponseEntity<Void> duplicateEmail(@RequestParam String email) {
        if (duplicateMemberDetailsQuery.isDuplicateEmail(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/members/nickname")
    public ResponseEntity<Void> duplicateNickname(@RequestParam String nickname) {
        if (duplicateMemberDetailsQuery.isDuplicateNickname(nickname)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
