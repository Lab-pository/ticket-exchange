package com.ticketlounge.member.acceptance;

import static com.ticketlounge.member.api.MemberApiSupporter.회원가입;
import static com.ticketlounge.member.fixture.MemberFixture.EMAIL;
import static com.ticketlounge.member.fixture.MemberFixture.NICKNAME;
import static com.ticketlounge.member.fixture.MemberFixture.로그인_요청;
import static com.ticketlounge.member.fixture.MemberFixture.회원가입_요청;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.ticketlounge.AcceptanceTest.AcceptanceTest;

import io.restassured.http.ContentType;

class MemberAcceptanceTest extends AcceptanceTest {

    @Nested
    class 중복된_이메일_검사_API_테스트 {

        @BeforeEach
        void setUp() {
            회원가입();
        }

        @Test
        void 중복된_이메일이_없는_경우_204_응답() {
            final var 중복되지_않은_이메일 = "not_duplicate@naver.com";
            final var 응답 = given()
                    .queryParam("email", 중복되지_않은_이메일)
                    .get("/api/v1/members/email")
                    .then()
                    .extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);
        }

        @Test
        void 중복된_이메일이_있는_경우_409_응답() {
            final var 응답 = given()
                    .queryParam("email", EMAIL)
                    .get("/api/v1/members/email")
                    .then()
                    .extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
        }
    }

    @Nested
    class 중복된_닉네임_검사_API_테스트 {

        @BeforeEach
        void setUp() {
            회원가입();
        }

        @Test
        void 중복된_닉네임이_없는_경우_204_응답() {
            final var 중복되지_않은_닉네임 = "not_duplicate";
            final var 응답 = given()
                    .queryParam("nickname", 중복되지_않은_닉네임)
                    .get("/api/v1/members/nickname")
                    .then()
                    .extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);
        }

        @Test
        void 중복된_닉네임이_있는_경우_409_응답() {
            final var 응답 = given()
                    .queryParam("nickname", NICKNAME)
                    .get("/api/v1/members/nickname")
                    .then()
                    .extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
        }
    }

    @Nested
    class 회원가입_API_테스트 {

        @Test
        void 정상적인_요청인_경우_회원가입에_성공한다() {
            final var 응답 = given()
                    .body(회원가입_요청())
                    .contentType(ContentType.JSON)
                    .post("/api/v1/members")
                    .then()
                    .extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertThat(응답.header(HttpHeaders.LOCATION)).isNotBlank();
        }
    }

    @Nested
    class 로그인_API_테스트 {

        @Test
        void 정상적인_요청인_경우_로그인에_성공한다() {
            회원가입();
            final var 응답 = given()
                    .body(로그인_요청())
                    .contentType(ContentType.JSON)
                    .post("/api/v1/login")
                    .then()
                    .extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_OK);
        }
    }
}
