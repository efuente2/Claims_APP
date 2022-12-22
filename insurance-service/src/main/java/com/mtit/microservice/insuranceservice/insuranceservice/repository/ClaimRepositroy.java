package com.mtit.microservice.insuranceservice.insuranceservice.repository;

import com.mtit.microservice.insuranceservice.insuranceservice.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepositroy extends JpaRepository<Claim, Long> {

}
