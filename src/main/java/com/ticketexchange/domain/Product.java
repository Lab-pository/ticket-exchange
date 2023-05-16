package com.ticketexchange.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;

	private String name;

	private Integer totalQuantity;

	private Integer remainQuantity;

	private Integer needTicketCount;

	private LocalDate validStartDate;

	private LocalDate validEndDate;

	protected Product() {
	}

	public Product(String name, Integer totalQuantity, Integer needTicketCount, LocalDate validStartDate,
		LocalDate validEndDate) {
		this.name = name;
		this.totalQuantity = totalQuantity;
		this.remainQuantity = totalQuantity;
		this.needTicketCount = needTicketCount;
		this.validStartDate = validStartDate;
		this.validEndDate = validEndDate;
	}
}
