package com.mtit.microservice.insuranceservice.insuranceservice.controller;

import com.mtit.microservice.insuranceservice.insuranceservice.dto.ClaimsResponse;
import com.mtit.microservice.insuranceservice.insuranceservice.dto.ClaimsRequest;
import com.mtit.microservice.insuranceservice.insuranceservice.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClaimsController {

    @Autowired
    private ClaimService paymentService;

    @PostMapping("/Claim")
    @ResponseStatus(HttpStatus.CREATED)
    public void newTransaction(@RequestBody ClaimsRequest paymentRequest){
        paymentService.newTransaction(paymentRequest);
    }

    @GetMapping("/Claim")



    @GetMapping("/Claim")
    @ResponseStatus(HttpStatus.OK)
    public List<ClaimsResponse> getAllPayments(){
        return paymentService.getAllClaims();
    }

    @GetMapping("/Claim/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClaimsResponse getPaymentById(@PathVariable("id") String id){
        return paymentService.getClaimByID(id);
    }


}
