package com.paymentapp.main.dto;

import jakarta.validation.constraints.NotBlank;

public class ApprovePaymentRequest {

	@NotBlank
	private String code; // PIN or OTP

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ApprovePaymentRequest(@NotBlank String code) {
		super();
		this.code = code;
	}

	public ApprovePaymentRequest() {
		super();
	}

}
