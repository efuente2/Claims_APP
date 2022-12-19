package com.mtit.microservice.insuranceservice.insuranceservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "date")
    private String date;

    @Column(name = "itemID")
    public String itemId;

    public int getid(){
        return id;
    }

    public  String getAmount(){
        return amount;
    }

    public String getDate(){
        return date;
    }

    public String getItemId(){return itemId;}


}
