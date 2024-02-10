package com.ticketlounge.web.product;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketlounge.application.product.port.in.ApplyProductUseCase;
import com.ticketlounge.application.product.port.in.CreateProductUseCase;
import com.ticketlounge.application.product.port.in.ProductQuery;
import com.ticketlounge.domain.auth.CurrentUser;
import com.ticketlounge.domain.auth.MemberToken;
import com.ticketlounge.web.common.ApiResult;
import com.ticketlounge.web.product.request.ProductRequest;
import com.ticketlounge.web.product.response.ApplyProductResponse;
import com.ticketlounge.web.product.response.ProductResponse;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ApplyProductUseCase applyProductUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final ProductQuery productQuery;

    public ProductController(
            final ApplyProductUseCase applyProductUseCase,
            final CreateProductUseCase createProductUseCase,
            final ProductQuery productQuery
    ) {
        this.applyProductUseCase = applyProductUseCase;
        this.createProductUseCase = createProductUseCase;
        this.productQuery = productQuery;
    }

    @PostMapping
    public ResponseEntity<ApiResult<ProductResponse>> registerProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = ProductResponse.from(
                createProductUseCase.registerProduct(productRequest.toCreateProductCommand())
        );

        return ResponseEntity.created(URI.create("/products/" + productResponse.productId()))
                .body(ApiResult.succeed(productResponse));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ApiResult<ApplyProductResponse>> applyProduct(
            @CurrentUser MemberToken token,
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(
                ApiResult.succeed(ApplyProductResponse.from(applyProductUseCase.applyProduct(token.getId(), productId))));
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<ProductResponse>>> findAllValidProducts() {
        List<ProductResponse> productResponses = productQuery.findAllByValid()
                .stream()
                .map(ProductResponse::from)
                .toList();

        return ResponseEntity.ok(ApiResult.succeed(productResponses));
    }

}
