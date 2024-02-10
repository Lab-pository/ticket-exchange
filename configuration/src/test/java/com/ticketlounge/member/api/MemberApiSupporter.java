package com.ticketlounge.member.api;

import static com.ticketlounge.member.fixture.MemberFixture.로그인_요청;
import static com.ticketlounge.member.fixture.MemberFixture.회원가입_요청;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public final class MemberApiSupporter {

    private MemberApiSupporter() {
    }

    public static ExtractableResponse<Response> 회원가입() {
        return given()
                .body(회원가입_요청())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/api/v1/members")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인() {
        return given()
                .body(로그인_요청())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/api/v1/login")
                .then()
                .extract();
    }
}
