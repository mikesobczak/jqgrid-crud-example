package com.val.jqgrid.demo.payload.response;

import java.util.ArrayList;
import java.util.List;

import com.val.jqgrid.demo.view.Item;

public class Payload {

	/**
	 * total pages for the query
	 */
	private Integer total;
	
	/**
	 * total number of records for the query
	 */
	private Integer records;
	
	private Integer pageSize;
	
	/**
	 * current page of the query
	 */
	private Integer page;
	
	/**
	 * an array that contains the actual data
	 */
	private List<Item> rows;
	
	public Payload () {
		rows = new ArrayList<Item>();
	}
	
	/**
	 * get the total pages for the query
	 */
	public Integer getTotal() {
		return total;
	}
	
	/**
	 * set the total pages for the query
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * get the total number of records for the query
	 */
	public Integer getRecords() {
		return records;
	}
	
	/**
	 * set the total number of records for the query
	 */
	public void setRecords(Integer records) {
		this.records = records;
	}
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * get current page of the query
	 */
	public Integer getPage() {
		return page;
	}
	
	/**
	 * set current page of the query
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	
	/**
	 * get the array that contains the actual data
	 */
	public List<Item> getRows() {
		return rows;
	}
	
	/**
	 * set the array that contains the actual data
	 */
	public void setRows(List<Item> rows) {
		this.rows = rows;
	}
	
}
