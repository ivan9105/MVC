package com.springapp.mvc.integration.shop.service;

import com.springapp.mvc.integration.shop.dto.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 05.12.2016.
 */
public class OnlineShop {
    private static final Log log = LogFactory.getLog(OnlineShop.class);

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("src/main/java/com/springapp/mvc/integration/shop/service/shop.xml");
        Shop shop = (Shop) context.getBean("shop");
        Order order = createOrder();
        log.debug("*** [OnlineShop] ****");
        shop.placeOrder(order);
        context.close();
    }

    private static Order createOrder() {
        Book book = new Book("Of Mice & Men", "Bluebird", new BigDecimal("100"), 1988, "John Steinbeck");
        MusicCD cd = new MusicCD("Off the Wall", "publisher", new BigDecimal("100"), 1975, "Michael Jackson");
        Software macos = new Software("Mavericks", "publisher", new BigDecimal("100"), 2014, "10.9.3");

        OrderItem bookItems = new OrderItem(book);
        OrderItem cdItems = new OrderItem(cd);
        OrderItem swItems = new OrderItem(macos);

        final List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(bookItems);
        orderItems.add(cdItems);
        orderItems.add(swItems);

        Order order = new Order();
        order.setOrderItems(orderItems);
        log.debug("Order: " + order);

        return order;
    }
}
