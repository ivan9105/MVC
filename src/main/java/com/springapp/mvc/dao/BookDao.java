package com.springapp.mvc.dao;

import com.springapp.mvc.model.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by ���� on 30.07.2015.
 */
public interface BookDao {
    boolean saveBook(Book book);
    List<Book> getBooks();
    void updateBook(Book book);
    void deleteBook(Book book);
    Book getBook(UUID id);
    void indexBooks();
    long getCount(String searchText);
    List<Book> searchForBook(String searchText, Pageable pageRequest);
    List<Book> searchForBook(String searchText);
}
