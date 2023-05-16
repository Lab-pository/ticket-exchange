package com.ticketexchange.controller.dto;

import com.ticketexchange.service.dto.ApplyProductDto;

public class ApplyProductResponse {
	private boolean result;

	protected ApplyProductResponse() {
	}

	public ApplyProductResponse(boolean result) {
		this.result = result;
	}

	public static ApplyProductResponse of(ApplyProductDto applyProductDto) {
		return new ApplyProductResponse(applyProductDto.isResult());
	}

	public boolean isResult() {
		return result;
	}
}
