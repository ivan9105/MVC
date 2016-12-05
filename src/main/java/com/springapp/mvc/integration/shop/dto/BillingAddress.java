package com.springapp.mvc.integration.shop.dto;

import java.io.Serializable;

/**
 * Created by Иван on 05.12.2016.
 */
public class BillingAddress extends Address implements Serializable{
	private static final long serialVersionUID = -3977964168622401031L;

	public BillingAddress() {		
		type = AddressType.BILLING_ADDRESS;
	}

	public BillingAddress(String firstLineOfAddress, String city, String postCode) {
		super(firstLineOfAddress, city, postCode);
		type = AddressType.BILLING_ADDRESS;
	}
}
