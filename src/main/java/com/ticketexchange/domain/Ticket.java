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
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private boolean isUsed;

	private String howToAcquire;

	private LocalDate acquireDate;

	private String howToUse;

	private LocalDate usedDate;

	private boolean didWin;

	private LocalDate expireDate;

	protected Ticket() {
	}

	public Ticket(Member member, String howToAcquire) {
		LocalDate now = LocalDate.now();
		this.member = member;
		this.howToAcquire = howToAcquire;
		this.acquireDate = now;
		this.expireDate = now.plusDays(VALID_PERIOD);
	}
}
