package com.springapp.mvc.integration.shop.service;

import com.springapp.mvc.integration.shop.dto.Order;
import com.springapp.mvc.integration.shop.dto.OrderItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by Иван on 05.12.2016.
 */
public class OrderCompleter {
    private static final Log log = LogFactory.getLog(OrderCompleter.class);

    public Order prepareDelivery(List<OrderItem> orderItems) {
        final Order order = new Order();
        order.setOrderItems(orderItems);
        log.debug("*** [OrderCompleter] CompletedOrder Discounted cost: "
                + order.getTotalDiscountedCost() + " ****");
        return order;
    }
}
