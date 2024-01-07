package com.ticketexchange.product.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketexchange.product.application.port.in.ApplyProductUseCase;
import com.ticketexchange.product.application.port.out.EarnedProductPort;
import com.ticketexchange.product.application.port.out.ProductQueryPort;
import com.ticketexchange.product.domain.EarnedProduct;
import com.ticketexchange.product.domain.Product;
import com.ticketexchange.ticket.application.port.out.TicketQueryPort;
import com.ticketexchange.ticket.domain.Ticket;

@Service
@Transactional
public class ApplyProductService implements ApplyProductUseCase {

    private final ProductQueryPort productQueryPort;
    private final TicketQueryPort ticketQueryPort;
    private final EarnedProductPort earnedProductPort;

    public ApplyProductService(
            final ProductQueryPort productQueryPort, TicketQueryPort ticketQueryPort,
            final EarnedProductPort earnedProductPort
    ) {
        this.productQueryPort = productQueryPort;
        this.ticketQueryPort = ticketQueryPort;
        this.earnedProductPort = earnedProductPort;
    }

    @Override
    public EarnedProduct applyProduct(final Long memberId, final Long productId) {
        final LocalDate now = LocalDate.now();
        final Product product = productQueryPort.getById(productId);
        final List<Ticket> applicantTickets = ticketQueryPort.findAllByMemberIdWithValid(memberId, now);

        if (product.canApply(now)
                && hasEnoughTickets(applicantTickets, product.getNeedTicketCount())
                && product.isWin()
        ) {
            applicantTickets.subList(0, product.getNeedTicketCount()).forEach(t -> t.use(product.getName()));
            product.decreaseRemainQuantity();
            return earnedProductPort.save(new EarnedProduct(memberId, product));
        }
        return null;
    }

    private boolean hasEnoughTickets(final List<Ticket> applicantTickets, final long needTicketCount) {
        return applicantTickets.size() >= needTicketCount;
    }
}
