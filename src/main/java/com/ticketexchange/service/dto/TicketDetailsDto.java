package com.ticketexchange.service.dto;

import java.time.LocalDate;

import com.ticketexchange.domain.Ticket;

public class TicketDetailsDto {
	private Long ticketId;
	private String howToAcquire;
	private LocalDate acquireDate;
	private LocalDate expireDate;
	private boolean isUsed;
	private String howToUse;
	private LocalDate useDate;

	protected TicketDetailsDto() {
	}

	public TicketDetailsDto(Long ticketId, String howToAcquire, LocalDate acquireDate, LocalDate expireDate,
		boolean isUsed, String howToUse, LocalDate useDate) {
		this.ticketId = ticketId;
		this.howToAcquire = howToAcquire;
		this.acquireDate = acquireDate;
		this.expireDate = expireDate;
		this.isUsed = isUsed;
		this.howToUse = howToUse;
		this.useDate = useDate;
	}

	public static TicketDetailsDto of(Ticket ticket) {
		return new TicketDetailsDto(ticket.getId(), ticket.getHowToAcquire(), ticket.getAcquireDate(),
			ticket.getExpireDate(), ticket.isUsed(), ticket.getHowToUse(), ticket.getUsedDate());
	}

	public Long getTicketId() {
		return ticketId;
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

	public LocalDate getUseDate() {
		return useDate;
	}
}
