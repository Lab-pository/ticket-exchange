package com.ticketlounge.application.product.port.out;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.EarnedProduct;

@Component
public interface EarnedProductQueryPort {

    List<EarnedProduct> findAllByMemberId(final Long memberId);
}
