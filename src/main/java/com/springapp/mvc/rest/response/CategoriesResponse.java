package com.springapp.mvc.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapp.mvc.rest.dto.shop.CategoryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���� on 03.01.2017.
 */
public class CategoriesResponse {
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CategoryDto> categories = new ArrayList<>();

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
