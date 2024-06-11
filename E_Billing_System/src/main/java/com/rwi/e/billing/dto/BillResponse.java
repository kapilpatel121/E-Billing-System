package com.rwi.e.billing.dto;

import lombok.Data;

@Data
public class BillResponse {
	private Integer billId;
    private String billMessage;
    private String razorpayOrderId;
   

    // Constructors
    public BillResponse() {}

	public BillResponse(Integer billId, String billMessage, String razorpayOrderId) {
        this.billId=billId;
    	this.billMessage = billMessage;
        this.razorpayOrderId = razorpayOrderId;
        
    }
}