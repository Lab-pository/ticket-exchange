package com.ticketlounge.application.product.port.out;

import org.springframework.stereotype.Component;

import com.ticketlounge.domain.product.EarnedProduct;

@Component
public interface EarnedProductPort {

    EarnedProduct save(final EarnedProduct earnedProduct);
}
