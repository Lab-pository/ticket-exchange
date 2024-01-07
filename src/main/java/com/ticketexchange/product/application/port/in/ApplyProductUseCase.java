package com.ticketexchange.product.application.port.in;

import com.ticketexchange.product.domain.EarnedProduct;

public interface ApplyProductUseCase {

    EarnedProduct applyProduct(final Long memberId, final Long productId);

}
