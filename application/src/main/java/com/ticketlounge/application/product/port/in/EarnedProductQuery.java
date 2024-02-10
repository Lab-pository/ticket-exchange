package com.ticketlounge.application.product.port.in;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.EarnedProduct;

@Component
public interface EarnedProductQuery {

    List<EarnedProduct> getEarnedProductsOfMember(final Long memberId);
}
