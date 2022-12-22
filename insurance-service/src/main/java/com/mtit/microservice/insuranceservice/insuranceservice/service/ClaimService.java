package com.mtit.microservice.insuranceservice.insuranceservice.service;

import com.mtit.microservice.insuranceservice.insuranceservice.dto.ClaimsResponse;
import com.mtit.microservice.insuranceservice.insuranceservice.model.Claim;
import com.mtit.microservice.insuranceservice.insuranceservice.repository.ClaimRepositroy;
import com.mtit.microservice.insuranceservice.insuranceservice.dto.ClaimsRequest;
import com.mtit.microservice.insuranceservice.insuranceservice.dto.ProductResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClaimService {

    @Autowired
    private ClaimRepositroy claimRepositroy;
    @Autowired
    private WebClient.Builder webClient;

    public void newTransaction (ClaimsRequest paymentRequest){
        Claim claim = Claim.builder()
                .amount(paymentRequest.getAmount())
                .date(paymentRequest.getDate())
                .itemId(paymentRequest.getClaimId())
                .build();
        var resposnce  = webClient.build().get()
                .uri("http://localhost:9080/api/inventory/Product",
                        uriBuilder -> uriBuilder.queryParam("id", claim.getItemId()).build())
                .retrieve()
                .bodyToMono(ProductResponce[].class)
                .block();

        boolean check = false;
        int number = Integer.parseInt(paymentRequest.getClaimId());

        for (int i = 0; i< resposnce.length; i++){
            if(resposnce[i].getId() == number){
                check = true;
            }
        }
        if(check){
            claimRepositroy.save(claim);
            log.info("Claim " + claim.getid() + " is saved");
        }
        else {
            log.info("item not in stock");
        }
//        paymentRepositroy.save(payment);
//           log.info("Claim " + payment.getid() + " is saved");
    }

    public List<ClaimsResponse> getAllClaims(){
        List<Claim> paymentList = claimRepositroy.findAll();

        return paymentList.stream().map(this::mapToClaimResponse).toList();
    }

    private ClaimsResponse mapToClaimResponse(Claim payment) {
        return ClaimsResponse.builder()
                .id(payment.getid())
                .amount(payment.getAmount())
                .date(payment.getDate())
                .build();
    }

    public ClaimsResponse getClaimByID(String id){
        int finalId = Integer.parseInt(id);
        Optional<Claim> paymentList = claimRepositroy.findById((long)finalId);
        return (ClaimsResponse) paymentList.stream().map(this::mapToClaimResponse).toList();
    }

}
