package com.ticketexchange.service.dto;

public class ApplyProductDto {
	private boolean result;

	protected ApplyProductDto() {
	}

	public ApplyProductDto(boolean result) {
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}
}
