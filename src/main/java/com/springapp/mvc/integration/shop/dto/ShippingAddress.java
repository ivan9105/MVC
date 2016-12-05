package com.springapp.mvc.integration.shop.dto;

import java.io.Serializable;

/**
 * Created by Иван on 05.12.2016.
 */
public class ShippingAddress extends Address implements Serializable {
    private static final long serialVersionUID = 7858082156191079323L;

    public ShippingAddress() {
        type = AddressType.SHIPPING_ADDRESS;
    }

    public ShippingAddress(String firstLineOfAddress, String city, String postCode) {
        super(firstLineOfAddress, city, postCode);
        type = AddressType.SHIPPING_ADDRESS;
    }
}
