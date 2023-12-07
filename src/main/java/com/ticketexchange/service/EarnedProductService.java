package com.ticketexchange.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.auth.MemberToken;
import com.ticketexchange.repository.EarnedProductRepository;
import com.ticketexchange.service.dto.EarnedProductDto;

@Service
@Transactional
public class EarnedProductService {

    private final EarnedProductRepository earnedProductRepository;

    public EarnedProductService(final EarnedProductRepository earnedProductRepository) {
        this.earnedProductRepository = earnedProductRepository;
    }

    @Transactional(readOnly = true)
    public List<EarnedProductDto> getEarnedProducts(final MemberToken token) {
        return earnedProductRepository.findAllByAcquiredById(token.getId())
                .stream()
                .map(EarnedProductDto::from)
                .toList();
    }
}
