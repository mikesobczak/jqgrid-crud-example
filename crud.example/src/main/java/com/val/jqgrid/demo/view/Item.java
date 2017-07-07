package com.val.jqgrid.demo.view;

import java.util.Date;

public class Item {
	
	private Integer id;
	private String productName;
	private String uom;
	private Date startDate;
	private int qty;
	private double rate;
	private double investment;
	
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
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("id : " + id);
		sb.append("productName : " + productName);
		sb.append("uom : " + uom);
		sb.append("startDate : " + startDate);
		sb.append("qty : " + qty);
		sb.append("rate : " + rate);
		sb.append("investment : " + investment);
		
		return sb.toString();
	}
}
