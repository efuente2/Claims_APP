package com.mtit.microservice.insuranceservice.insuranceservice.controller;

import com.mtit.microservice.insuranceservice.insuranceservice.dto.PaymentResponse;
import com.mtit.microservice.insuranceservice.insuranceservice.dto.PaymentRequest;
import com.mtit.microservice.insuranceservice.insuranceservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/Payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void newTransaction(@RequestBody PaymentRequest paymentRequest){
        paymentService.newTransaction(paymentRequest);
    }

    @GetMapping("/Payment")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponse> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/Payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponse getPaymentById(@PathVariable("id") String id){
        return paymentService.getPaymentByID(id);
    }


}
