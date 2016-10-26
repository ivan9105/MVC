package com.springapp.mvc.data;

import com.springapp.mvc.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by Иван on 12.10.2016.
 */
public interface AuthorRepository extends PagingAndSortingRepository<Author, UUID> {
}
