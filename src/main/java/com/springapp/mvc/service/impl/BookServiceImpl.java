package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.BookDao;
import com.springapp.mvc.data.BookPagingRepository;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by ���� on 30.07.2015.
 */
@Transactional
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookPagingRepository bookPagingRepository;

    @Override
    public boolean saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public List<Book> getBooks(Pageable pageable) {
        return bookPagingRepository.findAll(pageable).getContent();
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }

    @Override
    public Book getBook(UUID id) {
        return bookDao.getBook(id);
    }

    @Override
    public long getCount() {
        return bookPagingRepository.count();
    }
}
