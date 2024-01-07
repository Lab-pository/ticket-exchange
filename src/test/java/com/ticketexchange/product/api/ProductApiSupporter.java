package com.ticketexchange.product.api;

import static com.ticketexchange.product.fixture.ProductFixture.상품_생성_요청;
import static io.restassured.RestAssured.given;

import org.springframework.http.MediaType;

import com.ticketexchange.product.adapter.in.web.request.ProductRequest;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public final class ProductApiSupporter {

    private ProductApiSupporter() {
    }

    public static ExtractableResponse<Response> 상품_생성() {
        return 상품_생성(상품_생성_요청());
    }

    public static ExtractableResponse<Response> 상품_생성(final ProductRequest 상품_생성_요청) {
        return given()
                .body(상품_생성_요청)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/products")
                .then().extract();
    }

    public static ExtractableResponse<Response> 상품_응모(final String 토큰, final Long 상품_ID) {
        return given()
                .header(new Header("X-AUTH-TOKEN", 토큰))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/products/{productId}", 상품_ID)
                .then().extract();
    }
}
