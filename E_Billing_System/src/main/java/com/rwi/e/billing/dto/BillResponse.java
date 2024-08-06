package com.rwi.e.billing.dto;

import lombok.Data;

import lombok.Data;

@Data
public class BillResponse {
    private String billMessage;
    private String razorpayOrderId;
   

    // Constructors
    public BillResponse() {}

    public BillResponse(String billMessage, String razorpayOrderId) {
        this.billMessage = billMessage;
        this.razorpayOrderId = razorpayOrderId;
        
    }
}