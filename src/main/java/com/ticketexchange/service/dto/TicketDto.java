package com.ticketexchange.service.dto;

import java.time.LocalDate;

import com.ticketexchange.domain.Ticket;

public class TicketDto {
	private String howToAcquire;
	private LocalDate acquireDate;
	private LocalDate expireDate;

	protected TicketDto() {
	}

	public TicketDto(String howToAcquire, LocalDate acquireDate, LocalDate expireDate) {
		this.howToAcquire = howToAcquire;
		this.acquireDate = acquireDate;
		this.expireDate = expireDate;
	}

	public static TicketDto of(Ticket ticket) {
		return new TicketDto(ticket.getHowToAcquire(), ticket.getAcquireDate(), ticket.getExpireDate());
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
}
