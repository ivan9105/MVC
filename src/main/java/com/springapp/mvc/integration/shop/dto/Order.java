package com.springapp.mvc.integration.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 05.12.2016.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -5362053016861873420L;

    private int orderId;

    private List<OrderItem> orderItems;

    public Order() {
        orderItems = new ArrayList<OrderItem>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalCost() {
        BigDecimal total = new BigDecimal(0);

        for (OrderItem orderItem : orderItems) {

            total = total.add(orderItem.getTotalPrice());
        }

        return total;
    }

    public BigDecimal getTotalDiscountedCost() {
        BigDecimal total = new BigDecimal(0);

        for (OrderItem orderItem : orderItems) {

            total = total.add(orderItem.getDiscountedPrice());
        }

        return round(total);
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    /**
     * Round for currency values
     *
     * @param value
     * @return
     */
    private BigDecimal round(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order [orderId=").append(orderId).append("] ");
        for (OrderItem orderItem : orderItems) {
            sb.append("\n");
            sb.append("item: ").append(orderItem.getItem().title);
            sb.append(" quantity : ").append(orderItem.getCount());
            sb.append(" price: ").append(orderItem.getItem().getPrice());
            sb.append(" total price: ").append(orderItem.getTotalPrice());
        }

        sb.append("\n");
        sb.append("total price: ").append(this.getTotalCost());

        return sb.toString();
    }
}

