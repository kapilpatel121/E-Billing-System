package com.rwi.e.billing.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="Product_Details")
public class Product_Entity {
	@Id
	@SequenceGenerator(name = "prodgen1",sequenceName = "Prod_no_seq1",initialValue =1001,allocationSize = 1 )
	@GeneratedValue(generator = "prodgen1",strategy = GenerationType.SEQUENCE)
    @Column(name="Id")
	private int Id;
	@Column(name ="name",nullable = false)
	private String name;
	@Column(name ="Purchase_Price",nullable = false)
	private Double purchasePrice;
	@Column(name ="price")
	private Double price;
	@Column(name="mfd",nullable = false)
	private Date mfd;
	@Column(name="exp",nullable = false)
	private Date exp;
	@Column(name="units",nullable = false)
	private int units;
	@Column(name="discount")
	private double discount;
	@Column(name="gst")
	private double gst;
	@Column(name="company_name")
	private String company_name;
	
	
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
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
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
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
 	
}
