package com.ticketexchange.product.application.port.out;

import com.ticketexchange.product.domain.Product;

public interface ProductPort {

    Product save(final Product product);


}
