package com.ticketexchange.controller.dto;

import java.time.LocalDate;

import com.ticketexchange.service.dto.ProductDto;

public class ProductResponse {
	private Long productId;
	private String name;
	private LocalDate validStartDate;
	private LocalDate validEndDate;
	private Integer totalQuantity;
	private Integer remainQuantity;
	private Integer needTicketCount;
	private Double probability;

	protected ProductResponse() {
	}

	public ProductResponse(Long productId, String name, LocalDate validStartDate, LocalDate validEndDate,
		Integer totalQuantity, Integer remainQuantity, Integer needTicketCount, Double probability) {
		this.productId = productId;
		this.name = name;
		this.validStartDate = validStartDate;
		this.validEndDate = validEndDate;
		this.totalQuantity = totalQuantity;
		this.remainQuantity = remainQuantity;
		this.needTicketCount = needTicketCount;
		this.probability = probability;
	}

	public static ProductResponse of(ProductDto productDto) {
		return new ProductResponse(productDto.getProductId(), productDto.getName(), productDto.getValidStartDate(),
			productDto.getValidEndDate(), productDto.getTotalQuantity(), productDto.getRemainQuantity(),
			productDto.getNeedTicketCount(), productDto.getProbability());
	}

	public Long getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public LocalDate getValidStartDate() {
		return validStartDate;
	}

	public LocalDate getValidEndDate() {
		return validEndDate;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public Integer getRemainQuantity() {
		return remainQuantity;
	}

	public Integer getNeedTicketCount() {
		return needTicketCount;
	}

	public Double getProbability() {
		return probability;
	}
}
