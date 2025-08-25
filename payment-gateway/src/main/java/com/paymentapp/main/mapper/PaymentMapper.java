package com.paymentapp.main.mapper;

import org.springframework.stereotype.Component;

import com.paymentapp.main.dto.PaymentResponse;
import com.paymentapp.main.entity.Payment;

@Component
public class PaymentMapper {

	public PaymentResponse toResponse(Payment p) {
		PaymentResponse r = new PaymentResponse();
		r.setId(p.getId());
		r.setMethod(p.getMethod());
		r.setAmount(p.getAmount());
		r.setReference(p.getReference());
		r.setStatus(p.getStatus());
		r.setCreatedDate(p.getCreatedDate());
		r.setDescription(p.getDescription());
		return r;
	}

}
