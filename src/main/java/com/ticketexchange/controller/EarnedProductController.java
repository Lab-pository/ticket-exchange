package com.ticketexchange.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketexchange.auth.CurrentUser;
import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.controller.dto.EarnedProductResponse;
import com.ticketexchange.service.EarnedProductService;
import com.ticketexchange.support.web.ApiResult;

@RestController
@RequestMapping("/api/v1/earned-products")
public class EarnedProductController {

    private final EarnedProductService earnedProductService;

    public EarnedProductController(EarnedProductService earnedProductService) {
        this.earnedProductService = earnedProductService;
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<EarnedProductResponse>>> findAllEarnedProducts(
            @CurrentUser MemberToken token
    ) {
        return ResponseEntity.ok(ApiResult.succeed(
                earnedProductService.getEarnedProducts(token).stream().map(EarnedProductResponse::from).toList()
        ));
    }

}
