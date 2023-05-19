package com.ticketexchange.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketexchange.auth.CurrentUser;
import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.controller.dto.TicketDetailsResponses;
import com.ticketexchange.controller.dto.TicketRequest;
import com.ticketexchange.controller.dto.TicketResponse;
import com.ticketexchange.service.TicketService;
import com.ticketexchange.support.web.ApiResult;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping
	public ResponseEntity<ApiResult<TicketResponse>> createTicket(
		@CurrentUser MemberToken token,
		@RequestBody TicketRequest ticketRequest) {
		TicketResponse ticketResponse = TicketResponse.of(
			ticketService.createTicket(token, ticketRequest.toCreateTicketDto()));

		return ResponseEntity.created(URI.create("/tickets"))
			.body(ApiResult.succeed(ticketResponse));
	}

	@GetMapping
	public ResponseEntity<ApiResult<TicketDetailsResponses>> findAllTicketsByMember(
		@CurrentUser MemberToken token
	) {
		return ResponseEntity.ok(
			ApiResult.succeed(TicketDetailsResponses.of(ticketService.findAllTicketsByMember(token)))
		);
	}
}
