package com.paymentapp.main.dto;

import java.math.BigDecimal;

import com.paymentapp.main.entity.PaymentMethod;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreatePaymentRequest {

	@NotNull
	private PaymentMethod method;
	@NotNull
	@DecimalMin("0.01")
	private BigDecimal amount;
	@NotBlank
	private String reference;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreatePaymentRequest(@NotNull PaymentMethod method, @NotNull @DecimalMin("0.01") BigDecimal amount,
			@NotBlank String reference, String description) {
		super();
		this.method = method;
		this.amount = amount;
		this.reference = reference;
		this.description = description;
	}

	public CreatePaymentRequest() {
		super();
	}

}
