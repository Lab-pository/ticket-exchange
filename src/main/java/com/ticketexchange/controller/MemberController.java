package com.ticketexchange.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	private final Logger log = LoggerFactory.getLogger(getClass());
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

	@GetMapping("/members/email")
	public ResponseEntity<Void> duplicateEmail(@RequestParam String email) {
		if (memberService.isDuplicateEmail(email)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/members/nickname")
	public ResponseEntity<Void> duplicateNickname(@RequestParam String nickname) {
		if (memberService.isDuplicateNickname(nickname)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
