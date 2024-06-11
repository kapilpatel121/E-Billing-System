package com.rwi.e.billing.Entity;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.transaction.Transactional;
import lombok.Data;


    @Entity
    @Data
    public class PaymentInfo {

        @Id
        private String orderId;
        private String paymentId;
        private String signature;
        private String paymentMode;
        private Double amount;
        @CreationTimestamp
        private LocalDateTime timestamp;
        @OneToOne
        @JoinColumn(name = "bill_id", referencedColumnName = "id")
        private Bill_Entity bill;

        // Getters and setters
  


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Bill_Entity getBill() {
        return bill;
    }

    public void setBill(Bill_Entity bill) {
        this.bill = bill;
    }
}
