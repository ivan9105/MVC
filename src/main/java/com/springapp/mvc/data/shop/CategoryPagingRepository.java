package com.springapp.mvc.data.shop;

import com.springapp.mvc.model.shop.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by Иван on 17.12.2016.
 */
public interface CategoryPagingRepository extends PagingAndSortingRepository<Category, UUID> {
    @Query(value = "select c from Category c " +
            "where c.name LIKE CONCAT('%',:text,'%') or c.description LIKE CONCAT('%',:text,'%')")
    List<Category> findAll(@Param("text") String text);

    @Query(value = "select c from Category c where c.level = 0")
    List<Category> findRoots();
}
