package com.springapp.mvc.model.shop;

import com.springapp.mvc.model.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Иван on 15.12.2016.
 */
@Entity
@Table(name = "SHOP_ITEM")
public class Item extends StandardEntity {
    @Column(name = "NAME_")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COUNT_")
    private Integer count;

    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull()
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
