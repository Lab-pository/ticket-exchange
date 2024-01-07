package com.ticketexchange.ticket.adapter.out.persistence;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "ticket")
public class TicketJpaEntity {

    @Transient
    private static final int VALID_PERIOD = 180;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    private Long memberId;

    private String howToAcquire;

    private LocalDate acquireDate;

    private LocalDate expireDate;

    private boolean isUsed;

    private String howToUse;

    private LocalDate usedDate;

    protected TicketJpaEntity() {
    }

    public TicketJpaEntity(final Long memberId, final String howToAcquire) {
        this(memberId, howToAcquire, LocalDate.now());
    }

    public TicketJpaEntity(final Long memberId, final String howToAcquire, final LocalDate now) {
        this.memberId = memberId;
        this.howToAcquire = howToAcquire;
        this.acquireDate = now;
        this.expireDate = now.plusDays(VALID_PERIOD);
    }

    public void use(String productName) {
        this.isUsed = true;
        this.usedDate = LocalDate.now();
        this.howToUse = productName;
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
