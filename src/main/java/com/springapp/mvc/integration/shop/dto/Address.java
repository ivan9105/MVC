package com.springapp.mvc.integration.shop.dto;

import java.io.Serializable;

/**
 * Created by Иван on 05.12.2016.
 */
public class Address implements Serializable{
	private static final long serialVersionUID = 1769559052818415322L;

	private String firstLineOfAddress;
	
	private String city;
	
	private String postCode;
	
	protected enum AddressType {
		BILLING_ADDRESS,
		SHIPPING_ADDRESS;
	};
	
	protected AddressType type;
	
	public Address() {		
	}

	public Address(String firstLineOfAddress, String city, String postCode) {
		this.firstLineOfAddress = firstLineOfAddress;
		this.city = city;
		this.postCode = postCode;
	}
	
	public String getFirstLineOfAddress() {
		return firstLineOfAddress;
	}

	public void setFirstLineOfAddress(String firstLineOfAddress) {
		this.firstLineOfAddress = firstLineOfAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}
}
