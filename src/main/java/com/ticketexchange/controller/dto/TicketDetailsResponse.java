package com.ticketexchange.controller.dto;

import java.time.LocalDate;

import com.ticketexchange.service.dto.TicketDetailsDto;

public class TicketDetailsResponse {
	private Long ticketId;
	private String howToAcquire;
	private LocalDate acquireDate;
	private LocalDate expireDate;
	private boolean isUsed;
	private String howToUse;
	private LocalDate useDate;

	protected TicketDetailsResponse() {
	}

	public TicketDetailsResponse(Long ticketId, String howToAcquire, LocalDate acquireDate, LocalDate expireDate,
		boolean isUsed, String howToUse, LocalDate useDate) {
		this.ticketId = ticketId;
		this.howToAcquire = howToAcquire;
		this.acquireDate = acquireDate;
		this.expireDate = expireDate;
		this.isUsed = isUsed;
		this.howToUse = howToUse;
		this.useDate = useDate;
	}

	public static TicketDetailsResponse of(TicketDetailsDto ticketDetailsDto) {
		return new TicketDetailsResponse(ticketDetailsDto.getTicketId(), ticketDetailsDto.getHowToAcquire(),
			ticketDetailsDto.getAcquireDate(), ticketDetailsDto.getExpireDate(), ticketDetailsDto.isUsed(),
			ticketDetailsDto.getHowToUse(), ticketDetailsDto.getUseDate());
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
