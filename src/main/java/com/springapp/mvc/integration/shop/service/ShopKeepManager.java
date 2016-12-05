package com.springapp.mvc.integration.shop.service;

/**
 * Created by Иван on 05.12.2016.
 */

import com.springapp.mvc.integration.shop.dto.Book;
import com.springapp.mvc.integration.shop.dto.MusicCD;
import com.springapp.mvc.integration.shop.dto.OrderItem;
import com.springapp.mvc.integration.shop.dto.Software;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;

/**
 * Created by Иван on 05.12.2016.
 */
public class ShopKeepManager {
    private static final Log log = LogFactory.getLog(ShopKeepManager.class);

    private static final BigDecimal BOOK_DISCOUNT = new BigDecimal(0.05);
    private static final BigDecimal MUSIC_DISCOUNT = new BigDecimal(0.10);
    private static final BigDecimal SOFTWARE_DISCOUNT = new BigDecimal(0.15);

    public OrderItem processBooks(OrderItem bookOrderItem) {
        log.debug("*** [Shopkeeper] processing book : " + bookOrderItem.getItem().getTitle() + " ****");
        BigDecimal finalPrice = calculateDiscountedPrice(bookOrderItem, BOOK_DISCOUNT);
        bookOrderItem.setDiscountedPrice(finalPrice);
        return bookOrderItem;
    }

    public OrderItem processMusic(OrderItem musicOrderItem) {
        log.debug("*** [Shopkeeper] processing music : " + musicOrderItem.getItem().getTitle() + " ****");
        BigDecimal finalPrice = calculateDiscountedPrice(musicOrderItem, MUSIC_DISCOUNT);
        musicOrderItem.setDiscountedPrice(finalPrice);
        return musicOrderItem;
    }

    public OrderItem processSoftware(OrderItem softwareOrderItem) {
        log.debug("*** [Shopkeeper] processing software : " + softwareOrderItem.getItem().getTitle() + " ****");
        BigDecimal finalPrice = calculateDiscountedPrice(softwareOrderItem, SOFTWARE_DISCOUNT);
        softwareOrderItem.setDiscountedPrice(finalPrice);
        return softwareOrderItem;
    }

    private BigDecimal calculateDiscountedPrice(OrderItem orderItem, BigDecimal discount) {
        BigDecimal discountedPrice = round(orderItem.getTotalPrice().multiply(discount));
        BigDecimal finalPrice = round(orderItem.getTotalPrice().subtract(discountedPrice));
        log.debug("item (" + getItemType(orderItem) + ") " +
                "item price: " + orderItem.getItem().getPrice() +
                " discount: " + discountedPrice +
                " final price: " + finalPrice);
        return finalPrice;
    }

    private String getItemType(OrderItem orderItem) {
        String type = "";
        if (orderItem.getItem() instanceof Book) {
            type = "Book: " + orderItem.getItem().getTitle();
        } else if (orderItem.getItem() instanceof MusicCD) {
            type = "MusicCD: " + orderItem.getItem().getTitle();
        } else if (orderItem.getItem() instanceof Software) {
            type = "Software: " + orderItem.getItem().getTitle();
        }
        return type;
    }

    private BigDecimal round(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}

