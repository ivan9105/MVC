package com.springapp.mvc.data;

import com.springapp.mvc.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by Иван on 09.10.2016.
 */
public interface BookPagingRepository extends PagingAndSortingRepository<Book, UUID> {
}
