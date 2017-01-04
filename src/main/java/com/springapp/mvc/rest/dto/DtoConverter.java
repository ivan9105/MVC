package com.springapp.mvc.rest.dto;

import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.model.shop.Item;
import com.springapp.mvc.rest.dto.shop.CategoryDto;
import com.springapp.mvc.rest.dto.shop.ItemDto;
import com.springapp.mvc.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Иван on 03.01.2017.
 */
public class DtoConverter {
    public static CategoryDto toCategoryDto(Category category, HttpServletRequest request) {
        CategoryDto res = new CategoryDto();
        res.setId(category.getId().toString());
        res.setName(category.getName());
        res.setDescription(category.getDescription());
        res.setLevel(category.getLevel());
        res.setChild(new ArrayList<CategoryDto>());
        res.setParentId(category.getParent() != null ? category.getParent().getId().toString() : "");
        res.setLinkDto(new LinkDto());
        res.getLinkDto().setRel("self");
        res.getLinkDto().setHref(ServletUtil.getRemoteAddr(request) + "/api/shop/category?id=" + res.getId());
        return res;
    }

    public static ItemDto toItemDto(Item item, HttpServletRequest request) {
        ItemDto res = new ItemDto();
        res.setId(item.getId().toString());
        res.setName(item.getName());
        res.setDescription(item.getDescription());
        res.setCount(item.getCount());
        res.setPrice(item.getPrice());
        res.setCategoryId(item.getCategory() != null ? item.getCategory().getId().toString() : "");
        res.setLinkDto(new LinkDto());
        res.getLinkDto().setRel("self");
        res.getLinkDto().setHref(ServletUtil.getRemoteAddr(request) + "/api/shop/item?id=" + res.getId());
        return res;
    }
}

