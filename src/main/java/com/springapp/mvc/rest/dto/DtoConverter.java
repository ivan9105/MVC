package com.springapp.mvc.rest.dto;

import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.rest.dto.shop.CategoryDto;
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
        res.getLinkDto().setHref(ServletUtil.getRemoteAddr(request) + "/api/category?id=" + res.getId());
        return res;
    }
}

