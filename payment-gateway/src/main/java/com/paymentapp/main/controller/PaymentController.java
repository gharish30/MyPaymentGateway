package com.paymentapp.main.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentapp.main.dto.ApprovePaymentRequest;
import com.paymentapp.main.dto.CreatePaymentRequest;
import com.paymentapp.main.dto.PaymentResponse;
import com.paymentapp.main.entity.Payment;
import com.paymentapp.main.mapper.PaymentMapper;
import com.paymentapp.main.service.PaymentService;

import jakarta.validation.Valid;

// Controller
@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private final PaymentService service;
    private final PaymentMapper mapper;

    public PaymentController(PaymentService service, PaymentMapper mapper) {
        this.service = service; this.mapper = mapper;
    }

    @PostMapping
    public PaymentResponse create(@Valid @RequestBody CreatePaymentRequest req) {
        return mapper.toResponse(service.create(req));
    }

    @PostMapping("/{id}/approve")
    public PaymentResponse approve(@PathVariable Long id, @Valid @RequestBody ApprovePaymentRequest req) {
        return mapper.toResponse(service.approve(id, req));
    }

    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable Long id) {
        return mapper.toResponse(service.get(id));
    }

    @GetMapping
    public Page<PaymentResponse> list(@RequestParam(defaultValue="0") int page,
                                      @RequestParam(defaultValue="10") int size) {
        Page<Payment> pg = service.list(PageRequest.of(page, size));
        return pg.map(mapper::toResponse);
    }

}
