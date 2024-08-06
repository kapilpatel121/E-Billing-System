package com.rwi.e.billing.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Bill_Details")
public class Bill_Entity {

    @Id
    @SequenceGenerator(name = "billgen1", sequenceName = "Bill_no_seq1", initialValue = 101, allocationSize = 1)
    @GeneratedValue(generator = "billgen1", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email_Id", nullable = false)
    private String email;

    @Column(name = "Contact_Number", nullable = false)
    private String contactNo;

    @Column(name = "Payment_Type", nullable = false)
    private String paymentType;

    @Column(name = "Total_Amount", nullable = false)
    private Double totalAmount;

    @Column(name = "transaction_Id", nullable = true)
    private String transactionId;

    @Column(name = "Purchase_Time", nullable = true)
    @CreationTimestamp
    private LocalDateTime purchaseTime;

    @Column(name = "Product_Count", nullable = true)
    private Integer noOfProduct;

    @Column(name = "pdfPath", nullable = true)
    private String pdfPath;

 
}
