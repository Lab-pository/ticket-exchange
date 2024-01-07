package com.ticketexchange.ticket.adapter.in.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketexchange.auth.CurrentUser;
import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.support.web.ApiResult;
import com.ticketexchange.ticket.adapter.in.web.request.TicketRequest;
import com.ticketexchange.ticket.adapter.in.web.response.TicketDetailsResponses;
import com.ticketexchange.ticket.adapter.in.web.response.TicketResponse;
import com.ticketexchange.ticket.application.port.in.CreateTicketUseCase;
import com.ticketexchange.ticket.application.port.in.TicketsOfMemberQuery;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final CreateTicketUseCase createTicketUseCase;
    private final TicketsOfMemberQuery ticketsOfMemberQuery;

    public TicketController(
            final CreateTicketUseCase createTicketUseCase,
            final TicketsOfMemberQuery ticketsOfMemberQuery
    ) {
        this.createTicketUseCase = createTicketUseCase;
        this.ticketsOfMemberQuery = ticketsOfMemberQuery;
    }

    @PostMapping
    public ResponseEntity<ApiResult<TicketResponse>> createTicket(
            @CurrentUser MemberToken token,
            @RequestBody TicketRequest ticketRequest
    ) {
        TicketResponse ticketResponse = TicketResponse.from(
                createTicketUseCase.createTicket(token, ticketRequest.toCreateTicketCommand()));

        return ResponseEntity.created(URI.create("/tickets"))
                .body(ApiResult.succeed(ticketResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResult<TicketDetailsResponses>> findAllTicketsByMember(
            @CurrentUser MemberToken token
    ) {
        return ResponseEntity.ok(
                ApiResult.succeed(TicketDetailsResponses.from(ticketsOfMemberQuery.getTicketsOfMember(token.getId())))
        );
    }
}
