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

    @Column(name = "LEVEL_")
    private Integer level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @ContainedIn
    protected List<Category> child;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return name;
    }
}
