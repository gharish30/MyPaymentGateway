package com.paymentapp.main.service;

import org.springframework.stereotype.Service;

import com.paymentapp.main.dto.ApprovePaymentRequest;
import com.paymentapp.main.dto.CreatePaymentRequest;
import com.paymentapp.main.entity.Payment;
import com.paymentapp.main.entity.PaymentMethod;
import com.paymentapp.main.entity.PaymentStatus;
import com.paymentapp.main.exception.BadRequestException;
import com.paymentapp.main.exception.NotFoundException;
import com.paymentapp.main.repository.PaymentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

	public static final String A_PAY_PIN = "123456";
	public static final String B_PAY_OTP = "999999";

	private PaymentRepository paymentRepository;

	public PaymentService(PaymentRepository paymentRepository) {
		super();
		this.paymentRepository = paymentRepository;
	}

	@Transactional
	public Payment create(CreatePaymentRequest req) {
		paymentRepository.findByReference(req.getReference()).ifPresent(p -> {
			throw new BadRequestException("Reference already exists");
		});
		Payment p = new Payment();
		p.setMethod(req.getMethod());
		p.setAmount(req.getAmount());
		p.setReference(req.getReference());
		p.setDescription(req.getDescription());
		p.setStatus(PaymentStatus.PENDING);
		return paymentRepository.save(p);
	}

	@Transactional(noRollbackFor = BadRequestException.class)
	public Payment approve(Long id, ApprovePaymentRequest req) {
		Payment p = paymentRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found"));
		if (p.getStatus() != PaymentStatus.PENDING)
			throw new BadRequestException("Payment already processed");

		if (p.getMethod() == PaymentMethod.B_PAY) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ignored) {
			}
		}

		boolean ok = switch (p.getMethod()) {
		case A_PAY -> A_PAY_PIN.equals(req.getCode());
		case B_PAY -> B_PAY_OTP.equals(req.getCode());
		};

		if (!ok) {
			p.setStatus(PaymentStatus.FAILED);
			paymentRepository.save(p);
			throw new BadRequestException("Invalid PIN/OTP");
		}

		p.setStatus(PaymentStatus.SUCCESS);
		return p;
	}

	@Transactional(readOnly = true)
	public Payment get(Long id) {
		return paymentRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found"));
	}

	@Transactional(readOnly = true)
	public Page<Payment> list(Pageable pageable) {
		return paymentRepository.findAll(pageable);
	}

}
