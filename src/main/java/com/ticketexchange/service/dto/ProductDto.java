package com.ticketexchange.service.dto;

import java.time.LocalDate;

import com.ticketexchange.domain.Product;

public class ProductDto {
	private Long productId;
	private String name;
	private LocalDate validStartDate;
	private LocalDate validEndDate;
	private Integer totalQuantity;
	private Integer remainQuantity;
	private Integer needTicketCount;
	private Double probability;

	protected ProductDto() {
	}

	public ProductDto(Long productId, String name, LocalDate validStartDate, LocalDate validEndDate,
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

	public static ProductDto of(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getValidStartDate(),
			product.getValidEndDate(), product.getTotalQuantity(), product.getRemainQuantity(),
			product.getNeedTicketCount(), product.getProbability());
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
