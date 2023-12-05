package com.ticketexchange.acceptance.api;

import static com.ticketexchange.fixture.MemberFixture.로그인_요청;
import static com.ticketexchange.fixture.MemberFixture.회원가입_요청;
import static io.restassured.RestAssured.given;

import org.springframework.http.MediaType;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public final class MemberApiSupporter {

    private MemberApiSupporter() {
    }

    public static ExtractableResponse<Response> 회원가입() {
        return given()
                .body(회원가입_요청())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/members")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인() {
        return given()
                .body(로그인_요청())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/login")
                .then()
                .extract();
    }
}
