package com.ticketexchange.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.ticketexchange.service.dto.TicketDetailsDto;

public class TicketDetailsResponses {
	private int totalTicketCount;
	private int useTicketCount;
	private int validTicketCount;
	private List<TicketDetailsResponse> tickets;

	protected TicketDetailsResponses() {
	}

	public TicketDetailsResponses(int totalTicketCount, int useTicketCount, int validTicketCount,
		int expiredTicketCount,
		List<TicketDetailsResponse> tickets) {
		this.totalTicketCount = totalTicketCount;
		this.useTicketCount = useTicketCount;
		this.validTicketCount = validTicketCount;
		this.tickets = tickets;
	}

	public static TicketDetailsResponses of(List<TicketDetailsDto> ticketDtoList) {
		int totalTicketCount = ticketDtoList.size();
		int validTicketCount = 0;
		int useTicketCount = 0;
		int expiredTicketCount = 0;
		List<TicketDetailsResponse> tickets = new ArrayList<>();
		for (TicketDetailsDto ticketDto : ticketDtoList) {
			tickets.add(TicketDetailsResponse.of(ticketDto));
			if (ticketDto.isUsed()) {
				useTicketCount++;
			} else {
				validTicketCount++;
			}
		}
		return new TicketDetailsResponses(totalTicketCount, useTicketCount, validTicketCount, expiredTicketCount,
			tickets);
	}

	public int getTotalTicketCount() {
		return totalTicketCount;
	}

	public int getUseTicketCount() {
		return useTicketCount;
	}

	public int getValidTicketCount() {
		return validTicketCount;
	}

	public List<TicketDetailsResponse> getTickets() {
		return tickets;
	}
}
