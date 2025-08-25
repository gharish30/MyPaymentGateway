package com.paymentapp.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.paymentapp.main.dto.ApprovePaymentRequest;
import com.paymentapp.main.dto.CreatePaymentRequest;
import com.paymentapp.main.entity.Payment;
import com.paymentapp.main.entity.PaymentMethod;
import com.paymentapp.main.entity.PaymentStatus;
import com.paymentapp.main.exception.BadRequestException;
import com.paymentapp.main.service.PaymentService;

@Transactional
@SpringBootTest
public class PaymentServiceTest {

	@Autowired
	PaymentService service;

	@Test
	void createAndApprove_APAY_Success() {
		CreatePaymentRequest c = new CreatePaymentRequest();
		c.setMethod(PaymentMethod.A_PAY);
		c.setAmount(new java.math.BigDecimal("5.00"));
		c.setReference("R1");
		Payment p = service.create(c);
		assertEquals(PaymentStatus.PENDING, p.getStatus());

		ApprovePaymentRequest a = new ApprovePaymentRequest();
		a.setCode(PaymentService.A_PAY_PIN);
		Payment approved = service.approve(p.getId(), a);
		assertEquals(PaymentStatus.SUCCESS, approved.getStatus());
	}

	@Test
	void approve_WrongCode_Failed() {
		CreatePaymentRequest c = new CreatePaymentRequest();
		c.setMethod(PaymentMethod.B_PAY);
		c.setAmount(new java.math.BigDecimal("5.00"));
		c.setReference("R2");
		Payment p = service.create(c);

		ApprovePaymentRequest a = new ApprovePaymentRequest();
		a.setCode("000000");
		Payment approved = service.approve(p.getId(), a);
		assertEquals(PaymentStatus.FAILED, approved.getStatus());
	}

	@Test
	void approve_AlreadyProcessed_BadRequest() {
		CreatePaymentRequest c = new CreatePaymentRequest();
		c.setMethod(PaymentMethod.A_PAY);
		c.setAmount(new java.math.BigDecimal("5.00"));
		c.setReference("R3");
		Payment p = service.create(c);

		ApprovePaymentRequest a = new ApprovePaymentRequest();
		a.setCode(PaymentService.A_PAY_PIN);
		service.approve(p.getId(), a);

		assertThrows(BadRequestException.class, () -> service.approve(p.getId(), a));
	}

}
