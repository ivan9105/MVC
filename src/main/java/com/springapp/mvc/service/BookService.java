package com.springapp.mvc.service;

import com.springapp.mvc.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by Иван on 30.07.2015.
 */
@Component
public interface BookService {
    boolean saveBook(Book book);
    List<Book> getBooks();
    void updateBook(Book book);
    void deleteBook(Book book);
    Book getBook(UUID id);
}
