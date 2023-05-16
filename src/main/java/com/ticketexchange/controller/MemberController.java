package com.ticketexchange.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketexchange.controller.dto.LoginRequest;
import com.ticketexchange.controller.dto.LoginResponse;
import com.ticketexchange.controller.dto.MemberRequest;
import com.ticketexchange.controller.dto.MemberResponse;
import com.ticketexchange.service.MemberService;
import com.ticketexchange.support.web.ApiResult;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/members")
	public ResponseEntity<ApiResult<MemberResponse>> createMember(@RequestBody MemberRequest memberRequest) {
		MemberResponse memberResponse = MemberResponse.of(
			memberService.createMember(memberRequest.toCreateMemberDto())
		);

		return ResponseEntity.created(URI.create("/members/" + memberResponse.getMemberId()))
			.body(ApiResult.succeed(memberResponse));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResult<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = LoginResponse.of(memberService.login(loginRequest.toLoginDto()));
		return ResponseEntity.ok(ApiResult.succeed(loginResponse));
	}
}
