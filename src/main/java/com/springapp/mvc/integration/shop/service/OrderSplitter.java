package com.springapp.mvc.integration.shop.service;

import com.springapp.mvc.integration.shop.dto.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;

/**
 * Created by Иван on 05.12.2016.
 */
public class OrderSplitter extends AbstractMessageSplitter {
    private static Log log = LogFactory.getLog(OrderSplitter.class);

    @Override
    protected Object splitMessage(Message<?> message) {
        log.debug("*** [OrderSplitter] splitting Order into it's constituent OrderItems : number of OrderItems: " +
                ((Order) message.getPayload()).getOrderItems().size() + " ****");
        return ((Order) message.getPayload()).getOrderItems();
    }
}
