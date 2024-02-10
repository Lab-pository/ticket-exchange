package com.ticketlounge.domain.ticket;

import java.time.LocalDate;

public class Ticket {

    private static final int VALID_PERIOD = 180;

    private final Long id;
    private final Long memberId;
    private final String howToAcquire;
    private final LocalDate acquireDate;
    private final LocalDate expireDate;
    private final boolean isUsed;
    private final String howToUse;
    private final LocalDate usedDate;

    public Ticket(final Long memberId) {
        this(null, memberId, "회원가입 기념 웰컴 티켓", LocalDate.now());
    }

    public Ticket(
            final Long id,
            final Long memberId,
            final String howToAcquire,
            final LocalDate acquireDate
    ) {
        this(id, memberId, howToAcquire, acquireDate, acquireDate.plusDays(VALID_PERIOD), false, null, null);
    }

    public Ticket(
            final Long id,
            final Long memberId,
            final String howToAcquire,
            final LocalDate acquireDate,
            final LocalDate expireDate,
            final boolean isUsed,
            final String howToUse,
            final LocalDate usedDate
    ) {
        this.id = id;
        this.memberId = memberId;
        this.howToAcquire = howToAcquire;
        this.acquireDate = acquireDate;
        this.expireDate = expireDate;
        this.isUsed = isUsed;
        this.howToUse = howToUse;
        this.usedDate = usedDate;
    }

    public Ticket use(final String productName) {
        return new Ticket(
                this.id,
                this.memberId,
                this.howToAcquire,
                this.acquireDate,
                this.expireDate,
                true,
                productName,
                LocalDate.now()
        );
    }

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public String getHowToAcquire() {
        return this.howToAcquire;
    }

    public LocalDate getAcquireDate() {
        return this.acquireDate;
    }

    public LocalDate getExpireDate() {
        return this.expireDate;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public String getHowToUse() {
        return this.howToUse;
    }

    public LocalDate getUsedDate() {
        return this.usedDate;
    }
}
