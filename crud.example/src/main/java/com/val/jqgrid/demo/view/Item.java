package com.val.jqgrid.demo.view;

import java.util.Date;

public class Item {
	
	private Integer id;
	private String productName;
	private String uom;
	private Date startDate;
	private int qty;
	private double rate;
	private float investment;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public float getInvestment() {
		return investment;
	}
	public void setInvestment(float investment) {
		this.investment = investment;
	}
	
	
	
}
