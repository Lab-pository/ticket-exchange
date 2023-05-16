package com.ticketexchange.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EarnedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member acquiredBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Product product;

	private LocalDate createdAt;

	protected EarnedProduct() {
	}

	public EarnedProduct(Member acquiredBy, Product product) {
		this.acquiredBy = acquiredBy;
		this.product = product;
		this.createdAt = LocalDate.now();
	}
}
