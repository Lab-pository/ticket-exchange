package com.ticketlounge.application.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketlounge.application.product.port.in.EarnedProductQuery;
import com.ticketlounge.application.product.port.out.EarnedProductQueryPort;
import com.ticketlounge.domain.product.EarnedProduct;

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
