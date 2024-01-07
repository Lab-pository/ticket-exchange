package com.ticketexchange.product.application.port.out;

import com.ticketexchange.product.domain.EarnedProduct;

public interface EarnedProductPort {

    EarnedProduct save(final EarnedProduct earnedProduct);
}
