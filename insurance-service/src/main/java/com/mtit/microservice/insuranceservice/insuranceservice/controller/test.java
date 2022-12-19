package com.mtit.microservice.insuranceservice.insuranceservice.controller;

public class test {


    public void test () {
        int[] practice = {1,4,5,6,2,8};

        int low = 0;
        int high = 0;

        for (int i = 0; i < practice.length; i++){
            int current = practice[i];
            if(i == 0){
                low = i;
                high = i;
            }
            if(current < low){
                low = current;
            }

            if(current > high){
                high = current;
            }
        }


        int secondLowInt = Integer.MIN_VALUE;
        int secondhighInt = Integer.MIN_VALUE;
        for (int i = 0; i < practice.length; i++){
                if(practice[i] < secondLowInt && practice[i] < low){
                    secondLowInt = practice[i];
                }
                if(practice[i] > secondhighInt && practice[i] > high){
                    secondhighInt = practice[i];
            }

        }

        System.out.println("this is the lowest: " + low+ "   this is the second lowest: " + secondLowInt + "   this is the highest: " + high + "   this is the second highest: " + secondhighInt);
    }

}
