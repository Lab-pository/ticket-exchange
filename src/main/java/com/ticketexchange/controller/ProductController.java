package com.ticketexchange.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketexchange.auth.CurrentUser;
import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.controller.dto.ApplyProductResponse;
import com.ticketexchange.controller.dto.ProductRequest;
import com.ticketexchange.controller.dto.ProductResponse;
import com.ticketexchange.service.ProductService;
import com.ticketexchange.support.web.ApiResult;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ApiResult<ProductResponse>> registerProduct(@RequestBody ProductRequest productRequest) {
		ProductResponse productResponse = ProductResponse.of(
			productService.registerProduct(productRequest.toCreateProductDto())
		);

		return ResponseEntity.created(URI.create("/products/" + productResponse.getProductId()))
			.body(ApiResult.succeed(productResponse));
	}

	@PostMapping("/{productId}")
	public ResponseEntity<ApiResult<ApplyProductResponse>> applyProduct(
		@CurrentUser MemberToken token,
		@PathVariable Long productId,
		@RequestParam LocalDate now
	) {
		return ResponseEntity.ok(ApiResult.succeed(
			ApplyProductResponse.of(productService.applyProduct(token, productId, now))
		));
	}

	@GetMapping
	public ResponseEntity<ApiResult<List<ProductResponse>>> findAllValidProducts(@RequestParam LocalDate now) {
		List<ProductResponse> productResponses = productService.findAllValidProducts(now)
			.stream()
			.map(ProductResponse::of)
			.toList();

		return ResponseEntity.ok(ApiResult.succeed(productResponses));
	}

}
