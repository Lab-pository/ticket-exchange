package com.ticketlounge.web.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketlounge.application.product.port.in.EarnedProductQuery;
import com.ticketlounge.domain.auth.CurrentUser;
import com.ticketlounge.domain.auth.MemberToken;
import com.ticketlounge.web.common.ApiResult;
import com.ticketlounge.web.product.response.EarnedProductResponse;

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
