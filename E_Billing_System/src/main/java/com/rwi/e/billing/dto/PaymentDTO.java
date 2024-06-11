package com.rwi.e.billing.dto;

import lombok.Data;

@Data
public class PaymentDTO {
	private String orderId;
    private String paymentId;
    private String signature;
    private String paymentMode;
    private Double amount;
    private Integer billId;

}
