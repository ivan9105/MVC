package com.springapp.mvc.data;

import com.springapp.mvc.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by Иван on 12.10.2016.
 */
public interface AuthorRepository extends CrudRepository<Author, UUID> {
}
