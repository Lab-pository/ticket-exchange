package com.ticketexchange.product.application.port.out;

import java.util.List;

import com.ticketexchange.product.domain.EarnedProduct;

public interface EarnedProductQueryPort {

    List<EarnedProduct> findAllByMemberId(final Long memberId);
}
