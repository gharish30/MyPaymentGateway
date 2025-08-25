package com.paymentapp.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentapp.main.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	public Optional<Payment> findByReference(String reference);
	
	
}
