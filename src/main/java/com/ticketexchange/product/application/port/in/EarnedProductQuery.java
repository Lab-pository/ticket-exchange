package com.ticketexchange.product.application.port.in;

import java.util.List;

import com.ticketexchange.product.domain.EarnedProduct;

public interface EarnedProductQuery {

    List<EarnedProduct> getEarnedProductsOfMember(final Long memberId);
}
