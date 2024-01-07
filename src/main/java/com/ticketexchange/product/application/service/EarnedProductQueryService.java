package com.ticketexchange.product.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.product.application.port.in.EarnedProductQuery;
import com.ticketexchange.product.application.port.out.EarnedProductQueryPort;
import com.ticketexchange.product.domain.EarnedProduct;

@Service
@Transactional(readOnly = true)
public class EarnedProductQueryService implements EarnedProductQuery {

    private final EarnedProductQueryPort earnedProductQueryPort;

    public EarnedProductQueryService(final EarnedProductQueryPort earnedProductQueryPort) {
        this.earnedProductQueryPort = earnedProductQueryPort;
    }

    @Override
    public List<EarnedProduct> getEarnedProductsOfMember(final Long memberId) {
        return earnedProductQueryPort.findAllByMemberId(memberId);
    }
}
