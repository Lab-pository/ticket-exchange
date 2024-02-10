package com.ticketlounge.application.product.port.in;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.EarnedProduct;

@Component
public interface ApplyProductUseCase {

    EarnedProduct applyProduct(final Long memberId, final Long productId);

}
