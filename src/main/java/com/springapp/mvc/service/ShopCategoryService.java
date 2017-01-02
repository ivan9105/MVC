package com.springapp.mvc.service;

import com.springapp.mvc.model.shop.Category;
import org.springframework.stereotype.Component;

/**
 * Created by Иван on 02.01.2017.
 */
@Component
public interface ShopCategoryService {
    void updateHierarchy(Category category);

    void removeHierarchy(Category category);
}
