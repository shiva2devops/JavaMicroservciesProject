package com.mongo.app.controller;

public class Customer {
	String name;
	String custId;
	String address;

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	String aadhar;
	public Customer(String name, String custId, String address, String aadhar) {
		super();
		this.name = name;
		this.custId = custId;
		this.address = address;
		this.aadhar=aadhar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
