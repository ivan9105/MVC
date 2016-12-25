package com.springapp.mvc.model.shop;

import com.springapp.mvc.model.StandardEntity;
import org.hibernate.search.annotations.ContainedIn;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Иван on 15.12.2016.
 */
@Entity
@Table(name = "SHOP_CATEGORY")
public class Category extends StandardEntity {
    @Column(name = "NAME_")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ContainedIn
    protected List<Item> items;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return name;
    }
}
