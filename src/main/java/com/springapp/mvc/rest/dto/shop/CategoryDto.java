package com.springapp.mvc.rest.dto.shop;

import com.springapp.mvc.rest.dto.BaseDto;

import java.util.List;

/**
 * Created by Иван on 03.01.2017.
 */
public class CategoryDto extends BaseDto {
    private String id;
    private String name;
    private String description;
    private Integer level;
    private String parentId;
    private Integer itemsCount = 0;
    private CategoryDto parent;
    private List<CategoryDto> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<CategoryDto> getChild() {
        return child;
    }

    public void setChild(List<CategoryDto> child) {
        this.child = child;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public CategoryDto getParent() {
        return parent;
    }

    public void setParent(CategoryDto parent) {
        this.parent = parent;
    }
}
