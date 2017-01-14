package com.springapp.mvc.service;

import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.rest.dto.shop.CategoryDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by ���� on 02.01.2017.
 */
@Component
public interface ShopCategoryService {
    void updateHierarchy(Category category);

    void removeHierarchy(Category category);

    List<CategoryDto> getCategoriesDto(UUID id, HttpServletRequest request);
}
