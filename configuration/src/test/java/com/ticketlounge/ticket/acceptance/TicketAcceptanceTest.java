package com.ticketlounge.ticket.acceptance;

import static com.ticketlounge.member.api.MemberApiSupporter.로그인;
import static com.ticketlounge.member.api.MemberApiSupporter.회원가입;
import static com.ticketlounge.ticket.fixture.TicketFixture.티켓_발급_요청;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.ticketlounge.AcceptanceTest.AcceptanceTest;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

class TicketAcceptanceTest extends AcceptanceTest {

    @Nested
    class 티켓_발급_API_테스트 {

        @Test
        void 정상적인_요청인_경우_티켓이_생성된다() {
            회원가입();
            final var 로그인_응답 = 로그인();
            final var 토큰 = 로그인_응답.body().jsonPath().getString("data.token");

            final var 응답 = given()
                    .header(new Header("X-AUTH-TOKEN", 토큰))
                    .body(티켓_발급_요청())
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/api/v1/tickets")
                    .then().extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertThat(응답.header(HttpHeaders.LOCATION)).isNotBlank();
        }
    }

    @Nested
    class 회원_전체_티켓_조회_API_테스트 {

        @Test
        void 정상적인_요청인_경우_전체_티켓이_조회된다() {
            회원가입();
            final var 로그인_응답 = 로그인();
            final var 토큰 = 로그인_응답.body().jsonPath().getString("data.token");

            final var 응답 = given()
                    .header(new Header("X-AUTH-TOKEN", 토큰))
                    .when()
                    .get("/api/v1/tickets")
                    .then().extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThat(응답.body().jsonPath().getString("data")).isNotBlank();
        }
    }
}
