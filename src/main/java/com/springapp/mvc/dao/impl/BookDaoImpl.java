package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.BookDao;
import com.springapp.mvc.model.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Иван on 30.07.2015.
 */
@Component
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean saveBook(Book book) {
        try {
            getCurrentSession().save(book);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getBooks() {
        return getCurrentSession().createQuery("from Book").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateBook(Book book) {
        getCurrentSession().save(book);
    }

    @Override
    public void deleteBook(Book book) {
        getCurrentSession().delete(book);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
