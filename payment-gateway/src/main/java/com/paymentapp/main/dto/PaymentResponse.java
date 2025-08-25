package com.paymentapp.main.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.paymentapp.main.entity.PaymentMethod;
import com.paymentapp.main.entity.PaymentStatus;

public class PaymentResponse {

	private Long id;
	private PaymentMethod method;
	private BigDecimal amount;
	private String reference;
	private PaymentStatus status;
	private LocalDateTime createdDate;
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

	public PaymentResponse(Long id, PaymentMethod method, BigDecimal amount, String reference, PaymentStatus status,
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

	public PaymentResponse(PaymentMethod method, BigDecimal amount, String reference, PaymentStatus status,
			LocalDateTime createdDate, String description) {
		super();
		this.method = method;
		this.amount = amount;
		this.reference = reference;
		this.status = status;
		this.createdDate = createdDate;
		this.description = description;
	}

	public PaymentResponse() {
		super();
	}

}
