package com.ticketexchange.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Ticket {

    @Transient
    private static final int VALID_PERIOD = 180;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String howToAcquire;

    private LocalDate acquireDate;

    private LocalDate expireDate;

    private boolean isUsed;

    private String howToUse;

    private LocalDate usedDate;

    protected Ticket() {
    }

    public Ticket(final Member member, final String howToAcquire) {
        this(member, howToAcquire, LocalDate.now());
    }

    public Ticket(final Member member, final String howToAcquire, final LocalDate now) {
        this.member = member;
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
        return id;
    }

    public Member getMember() {
        return member;
    }

    public String getHowToAcquire() {
        return howToAcquire;
    }

    public LocalDate getAcquireDate() {
        return acquireDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public String getHowToUse() {
        return howToUse;
    }

    public LocalDate getUsedDate() {
        return usedDate;
    }
}
