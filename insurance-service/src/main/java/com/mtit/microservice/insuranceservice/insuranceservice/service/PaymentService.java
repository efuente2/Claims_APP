package com.mtit.microservice.insuranceservice.insuranceservice.service;

import com.mtit.microservice.insuranceservice.insuranceservice.dto.PaymentResponse;
import com.mtit.microservice.insuranceservice.insuranceservice.repository.PaymentRepositroy;
import com.mtit.microservice.insuranceservice.insuranceservice.dto.PaymentRequest;
import com.mtit.microservice.insuranceservice.insuranceservice.dto.ProductResponce;
import com.mtit.microservice.insuranceservice.insuranceservice.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepositroy paymentRepositroy;
    @Autowired
    private WebClient.Builder webClient;

    public void newTransaction (PaymentRequest paymentRequest){
        Payment payment = Payment.builder()
                .amount(paymentRequest.getAmount())
                .date(paymentRequest.getDate())
                .itemId(paymentRequest.getItemId())
                .build();
        var resposnce  = webClient.build().get()
                .uri("http://localhost:9080/api/inventory/Product",
                        uriBuilder -> uriBuilder.queryParam("id", payment.getItemId()).build())
                .retrieve()
                .bodyToMono(ProductResponce[].class)
                .block();

        boolean check = false;
        int number = Integer.parseInt(paymentRequest.getItemId());

        for (int i = 0; i< resposnce.length; i++){
            if(resposnce[i].getId() == number){
                check = true;
            }
        }
        if(check){
            paymentRepositroy.save(payment);
            log.info("Payment " + payment.getid() + " is saved");
        }
        else {
            log.info("item not in stock");
        }
//        paymentRepositroy.save(payment);
//           log.info("Payment " + payment.getid() + " is saved");
    }

    public List<PaymentResponse> getAllPayments(){
        List<Payment> paymentList = paymentRepositroy.findAll();

        return paymentList.stream().map(this::mapToPaymentResponse).toList();
    }

    private PaymentResponse mapToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getid())
                .amount(payment.getAmount())
                .date(payment.getDate())
                .build();
    }

    public PaymentResponse getPaymentByID(String id){
        int finalId = Integer.parseInt(id);
        Optional<Payment> paymentList = paymentRepositroy.findById((long)finalId);
        return (PaymentResponse) paymentList.stream().map(this::mapToPaymentResponse).toList();
    }

}
