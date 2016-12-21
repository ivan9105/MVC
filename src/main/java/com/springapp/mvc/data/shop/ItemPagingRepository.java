package com.springapp.mvc.data.shop;

import com.springapp.mvc.model.shop.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by Иван on 17.12.2016.
 */
public interface ItemPagingRepository extends PagingAndSortingRepository<Item, UUID> {
}
