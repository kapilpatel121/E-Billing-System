package com.rwi.e.billing.dto;

import java.time.LocalDateTime;

public class Customer {
	 private Integer id;
	private String customerName;
    private String contactNumber;
    private String email;
    private String paymentMode;
    private double totalAmount;
    private String purchaseTime;
   private Integer noOfProduct;
    private String pdfPath;
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getNoOfProduct() {
		return noOfProduct;
	}
	public void setNoOfProduct(Integer noOfProduct) {
		this.noOfProduct = noOfProduct;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
   
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
    public Customer(String customerName, String contactNumber, String email, String paymentMode, double totalAmount) {
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.paymentMode = paymentMode;
        this.totalAmount = totalAmount;
    }
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", contactNumber=" + contactNumber + ", email=" + email
				+ ", paymentMode=" + paymentMode + ", totalAmount=" + totalAmount + ", noOfProduct=" + noOfProduct
				+ ", pdfPath=" + pdfPath + "]";
	}
    
  }