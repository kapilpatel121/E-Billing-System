package com.rwi.e.billing.dto;

import java.sql.Date;

public class Product_Dto {

	private int Id;
	private String name;
	private Double price;
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	private Double purchasePrice;
	private Date mfd;
	private Date exp;
	private int units;
	private double discount;
	private double gst;
	private String company_name;
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getMfd() {
		return mfd;
	}
	public void setMfd(Date mfd) {
		this.mfd = mfd;
	}
	public Date getExp() {
		return exp;
	}
	public void setExp(Date exp) {
		this.exp = exp;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	
	public Product_Dto() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product_Dto [Id=" + Id + ", name=" + name + ", price=" + price + ", purchasePrice=" + purchasePrice
				+ ", mfd=" + mfd + ", exp=" + exp + ", units=" + units + ", discount=" + discount + ", gst=" + gst
				+ ", company_name=" + company_name + "]";
	}
}
