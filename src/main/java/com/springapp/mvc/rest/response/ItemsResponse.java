package com.springapp.mvc.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapp.mvc.rest.dto.shop.ItemDto;

import java.util.List;

/**
 * Created by Иван on 04.01.2017.
 */
public class ItemsResponse {
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemDto> items;
    private Integer currentPage;
    private Integer pageSize;

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
