package com.springapp.mvc.integration.shop.service;

import com.springapp.mvc.integration.shop.dto.Order;
import org.springframework.integration.annotation.Gateway;

/**
 * Created by Иван on 05.12.2016.
 */
public interface Shop {
    @Gateway(requestChannel="ordersChannel")
    void placeOrder(Order order);
}
