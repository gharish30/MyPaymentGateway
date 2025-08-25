package com.paymentapp.main.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 16)
	private PaymentMethod method;

	@Column(nullable = false, precision = 16, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false, unique = true, length = 64)
	private String reference;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 16)
	private PaymentStatus status = PaymentStatus.PENDING;

	@Column(nullable = false)
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(length = 256)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Payment(Long id, PaymentMethod method, BigDecimal amount, String reference, PaymentStatus status,
			LocalDateTime createdDate, String description) {
		super();
		this.id = id;
		this.method = method;
		this.amount = amount;
		this.reference = reference;
		this.status = status;
		this.createdDate = createdDate;
		this.description = description;
	}

	public Payment(PaymentMethod method, BigDecimal amount, String reference, PaymentStatus status,
			LocalDateTime createdDate, String description) {
		super();
		this.method = method;
		this.amount = amount;
		this.reference = reference;
		this.status = status;
		this.createdDate = createdDate;
		this.description = description;
	}

	public Payment() {
		super();
	}
	
	

}
