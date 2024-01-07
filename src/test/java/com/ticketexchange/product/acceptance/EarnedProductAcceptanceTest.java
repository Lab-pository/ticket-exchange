package com.ticketexchange.product.acceptance;

import static com.ticketexchange.member.api.MemberApiSupporter.로그인;
import static com.ticketexchange.member.api.MemberApiSupporter.회원가입;
import static com.ticketexchange.product.api.ProductApiSupporter.상품_생성;
import static com.ticketexchange.product.api.ProductApiSupporter.상품_응모;
import static com.ticketexchange.product.fixture.ProductFixture.당첨되는_상품_생성_요청;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.ticketexchange.common.acceptance.AcceptanceTest;

import io.restassured.http.Header;

class EarnedProductAcceptanceTest extends AcceptanceTest {

    @Nested
    class 획득한_상품_조회_API {

        @Test
        void 정상적인_요청인_경우_성공적으로_조회한다() {
            회원가입();
            final var 토큰 = 로그인().body().jsonPath().getString("data.token");
            final var 상품_ID = 상품_생성(당첨되는_상품_생성_요청()).body().jsonPath().getLong("data.productId");
            상품_응모(토큰, 상품_ID);

            final var 응답 = given()
                    .header(new Header("X-AUTH-TOKEN", 토큰))
                    .when()
                    .get("/api/v1/earned-products")
                    .then().extract();

            assertThat(응답.statusCode()).isEqualTo(SC_OK);
            assertThat(응답.body().jsonPath().getList("data")).isNotEmpty();
        }
    }
}
