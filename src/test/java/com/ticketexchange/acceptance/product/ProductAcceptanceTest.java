package com.ticketexchange.acceptance.product;

import static com.ticketexchange.acceptance.api.MemberApiSupporter.로그인;
import static com.ticketexchange.acceptance.api.MemberApiSupporter.회원가입;
import static com.ticketexchange.acceptance.api.ProductApiSupporter.상품_생성;
import static com.ticketexchange.fixture.ProductFixture.당첨되는_상품_생성_요청;
import static com.ticketexchange.fixture.ProductFixture.당첨되지_않는_상품_생성_요청;
import static com.ticketexchange.fixture.ProductFixture.상품_생성_요청;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.ticketexchange.acceptance.AcceptanceTest;

import io.restassured.http.Header;

class ProductAcceptanceTest extends AcceptanceTest {

    @Nested
    class 상품_생성_API_테스트 {

        @Test
        void 정상적인_요청인_경우_상품이_생성된다() {
            final var 응답 = given()
                    .body(상품_생성_요청())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .post("/api/v1/products")
                    .then().extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertThat(응답.header(HttpHeaders.LOCATION)).isNotBlank();
        }
    }

    @Nested
    class 상품_응모하기_API_테스트 {

        @Test
        void 정상적인_요청인_경우_상품에_응모된다_당첨() {
            회원가입();
            final var 토큰 = 로그인().body().jsonPath().getString("data.token");
            final var 상품_ID = 상품_생성(당첨되는_상품_생성_요청()).body().jsonPath().getLong("data.productId");

            final var 응답 = given()
                    .header(new Header("X-AUTH-TOKEN", 토큰))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .post("/api/v1/products/{productId}", 상품_ID)
                    .then().extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThat(응답.body().jsonPath().getBoolean("data.result")).isTrue();
        }

        @Test
        void 정상적인_요청인_경우_상품에_응모된다_당첨실패() {
            회원가입();
            final var 토큰 = 로그인().body().jsonPath().getString("data.token");
            final var 상품_ID = 상품_생성(당첨되지_않는_상품_생성_요청()).body().jsonPath().getLong("data.productId");

            final var 응답 = given()
                    .header(new Header("X-AUTH-TOKEN", 토큰))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .post("/api/v1/products/{productId}", 상품_ID)
                    .then().extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThat(응답.body().jsonPath().getBoolean("data.result")).isFalse();
        }
    }

    @Nested
    class 상품_목록_조회_API_테스트 {

        @Test
        void 정상적인_요청인_경우_상품목록을_조회한다() {
            상품_생성();
            상품_생성();

            final var 응답 = given()
                    .when()
                    .get("/api/v1/products")
                    .then().extract();

            assertThat(응답.statusCode()).isEqualTo(HttpStatus.SC_OK);
        }
    }
}
