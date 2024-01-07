package com.ticketexchange.ticket.api;

import static com.ticketexchange.ticket.fixture.TicketFixture.티켓_발급_요청;
import static io.restassured.RestAssured.given;

import org.springframework.http.MediaType;

import com.ticketexchange.ticket.adapter.in.web.request.TicketRequest;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public final class TicketApiSupporter {

    private TicketApiSupporter() {
    }

    public static ExtractableResponse<Response> 티켓_발급(final String 토큰) {
        return given()
                .header(new Header("X-AUTH-TOKEN", 토큰))
                .body(티켓_발급_요청())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/tickets")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> 티켓_발급(final String 토큰, final TicketRequest 티켓_발급_요청) {
        return given()
                .header(new Header("X-AUTH-TOKEN", 토큰))
                .body(티켓_발급_요청)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/tickets")
                .then()
                .extract();
    }
}
