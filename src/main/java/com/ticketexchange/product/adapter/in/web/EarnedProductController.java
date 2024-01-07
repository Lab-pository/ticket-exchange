package com.ticketexchange.product.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketexchange.auth.CurrentUser;
import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.product.adapter.in.web.response.EarnedProductResponse;
import com.ticketexchange.product.application.port.in.EarnedProductQuery;
import com.ticketexchange.support.web.ApiResult;

@RestController
@RequestMapping("/api/v1/earned-products")
public class EarnedProductController {

    private final EarnedProductQuery earnedProductQuery;

    public EarnedProductController(final EarnedProductQuery earnedProductQuery) {
        this.earnedProductQuery = earnedProductQuery;
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<EarnedProductResponse>>> findAllEarnedProducts(
            @CurrentUser MemberToken token
    ) {
        return ResponseEntity.ok(ApiResult.succeed(
                earnedProductQuery.getEarnedProductsOfMember(token.getId()).stream().map(EarnedProductResponse::from)
                        .toList()
        ));
    }
}
