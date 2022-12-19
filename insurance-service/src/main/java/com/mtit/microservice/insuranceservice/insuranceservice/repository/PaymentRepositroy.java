package com.mtit.microservice.insuranceservice.insuranceservice.repository;

import com.mtit.microservice.insuranceservice.insuranceservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositroy extends JpaRepository<Payment, Long> {

}
