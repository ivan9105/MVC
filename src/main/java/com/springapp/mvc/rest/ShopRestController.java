package com.springapp.mvc.rest;

import com.springapp.mvc.data.shop.CategoryPagingRepository;
import com.springapp.mvc.data.shop.ItemPagingRepository;
import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.model.shop.Item;
import com.springapp.mvc.rest.dto.DtoConverter;
import com.springapp.mvc.rest.dto.shop.CategoryDto;
import com.springapp.mvc.rest.dto.shop.ItemDto;
import com.springapp.mvc.rest.response.CategoriesResponse;
import com.springapp.mvc.rest.response.ItemsResponse;
import com.springapp.mvc.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Иван on 03.01.2017.
 */
@RestController
@RequestMapping(ShopRestController.ROOT_PATH)
public class ShopRestController {
    public static final String ROOT_PATH = "/api/shop";

    @Autowired
    private CategoryPagingRepository categoryRepository;

    @Autowired
    private ItemPagingRepository itemRepository;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "category", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public CategoriesResponse getCategory(@RequestParam(value = "id", required = true) String idStr,
                                          HttpServletRequest request) {
        CategoriesResponse response = new CategoriesResponse();
        if (idStr == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Category category = categoryRepository.findOne(UUID.fromString(idStr));
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        response.setCategories(Collections.singletonList(DtoConverter.toCategoryDto(category, request)));
        return response;
    }

    @RequestMapping(value = "categories", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public CategoriesResponse getCategories(@RequestParam(value = "hierarchy", required = false)
                                            String hierarchy, HttpServletRequest request) {
        CategoriesResponse response = new CategoriesResponse();
        if (hierarchy != null && Boolean.parseBoolean(hierarchy)) {
            response.setCategories(shopCategoryService.getCategoriesDto(null, request));
        } else {
            Iterable<Category> categories = categoryRepository.findAll();
            response.setCategories(new ArrayList<CategoryDto>());
            for (Category category : categories) {
                response.getCategories().add(DtoConverter.toCategoryDto(category, request));
            }
        }
        return response;
    }

    @RequestMapping(value = "rootCategories", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public CategoriesResponse getRootCategories(HttpServletRequest request) {
        CategoriesResponse response = new CategoriesResponse();
        Iterable<Category> categories = categoryRepository.findRoots();
        for (Category category : categories) {
            response.getCategories().add(DtoConverter.toCategoryDto(category, request));
        }
        return response;
    }

    @RequestMapping(value = "childCategories", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public CategoriesResponse getChild(@RequestParam(value = "id", required = true) String id,
                                       HttpServletRequest request) {
        CategoriesResponse response = new CategoriesResponse();
        response.setCategories(shopCategoryService.getCategoriesDto(UUID.fromString(id), request));
        return response;
    }

    @RequestMapping(value = "items", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ItemsResponse getItems(HttpServletRequest request,
                                  @RequestParam(value = "size", required = true) Integer size,
                                  @RequestParam(value = "page", required = false) Integer page) {
        ItemsResponse response = new ItemsResponse();
        if (page == null || page == 0) {
            page = 1;
        }
        Iterable<Item> items = itemRepository.findAll(new PageRequest(page - 1, size));
        response.setItems(new ArrayList<ItemDto>());
        for (Item item : items) {
            response.getItems().add(DtoConverter.toItemDto(item, request));
        }
        response.setCurrentPage(page);
        Long count = itemRepository.getCount();
        response.setPageSize((int) Math.ceil(((double) Integer.valueOf(String.valueOf(count)) / size)));

        return response;
    }

    @RequestMapping(value = "item", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ItemsResponse getItem(@RequestParam(value = "id", required = true) String idStr,
                                 HttpServletRequest request) {
        ItemsResponse response = new ItemsResponse();
        if (idStr == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Item item = itemRepository.findOne(UUID.fromString(idStr));
        if (item == null) {
            throw new IllegalArgumentException("Item not found");
        }
        response.setItems(Collections.singletonList(DtoConverter.toItemDto(item, request)));
        return response;
    }

    @RequestMapping(value = "categoryItems", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ItemsResponse getItemsByCategory(@RequestParam(value = "categoryId", required = true) String categoryId,
                                            @RequestParam(value = "size", required = true) Integer size,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            HttpServletRequest request) {
        ItemsResponse response = new ItemsResponse();
        if (page == null || page == 0) {
            page = 1;
        }

        List<Item> items = itemRepository.findAll(UUID.fromString(categoryId), new PageRequest(page - 1, size));
        response.setItems(new ArrayList<ItemDto>());
        for (Item item : items) {
            response.getItems().add(DtoConverter.toItemDto(item, request));
        }
        response.setCurrentPage(page);
        Long count = itemRepository.getCount(UUID.fromString(categoryId));
        response.setPageSize((int) Math.ceil(((double) Integer.valueOf(String.valueOf(count)) / size)));

        return response;
    }

    @RequestMapping(value = "rootPath", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public CategoriesResponse getRootPath(@RequestParam(value = "categoryId", required = true) String categoryId,
                                          HttpServletRequest request) {
        CategoriesResponse response = new CategoriesResponse();
        response.setCategories(shopCategoryService.getRootPath(UUID.fromString(categoryId), request));
        return response;
    }
}
