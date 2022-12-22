package com.mtit.microservice.insuranceservice.insuranceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsRequest {
    private String amount;
    private String date;
    private String claimId;
}
