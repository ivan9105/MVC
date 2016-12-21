package com.springapp.mvc.data.shop;

import com.springapp.mvc.model.shop.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by Иван on 17.12.2016.
 */
public interface CategoryPagingRepository extends PagingAndSortingRepository<Category, UUID> {
}
