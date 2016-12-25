package com.springapp.mvc.data.shop;

import com.springapp.mvc.model.shop.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by Иван on 17.12.2016.
 */
public interface ItemPagingRepository extends PagingAndSortingRepository<Item, UUID> {
    @Query(value = "select i from Item i " +
            "where i.name LIKE CONCAT('%',:text,'%') or i.description LIKE CONCAT('%',:text,'%')")
    List<Item> findAll(@Param("text") String text);
}
