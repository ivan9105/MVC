package com.springapp.mvc.integration.shop.service;

import com.springapp.mvc.integration.shop.dto.Book;
import com.springapp.mvc.integration.shop.dto.MusicCD;
import com.springapp.mvc.integration.shop.dto.OrderItem;
import com.springapp.mvc.integration.shop.dto.Software;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Иван on 05.12.2016.
 */
public class OrderItemRouter {
    private static final Log log = LogFactory.getLog(OrderItemRouter.class);

    public String routeOrder(OrderItem orderItem) {
        log.debug("*** [OrderItemRouter] ****");
        String channel = "";
        if (isBook(orderItem)) {
            channel = "bookItemsChannel";
        } else if (isMusic(orderItem)) {
            channel = "musicItemsChannel";
        } else if (isSoftware(orderItem)) {
            channel = "softwareItemsChannel";
        }
        log.debug("*** [OrderItemRouter] sending item : " + orderItem.getItem().getTitle() + " to " + channel + " ****");

        return channel;
    }

    private Boolean isBook(OrderItem orderItem) {
        return orderItem.getItem() instanceof Book;
    }

    private Boolean isMusic(OrderItem orderItem) {
        return orderItem.getItem() instanceof MusicCD;
    }

    private Boolean isSoftware(OrderItem orderItem) {
        return orderItem.getItem() instanceof Software;
    }
}
