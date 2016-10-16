package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.BookDao;
import com.springapp.mvc.model.Book;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

/**
 * Created by ���� on 30.07.2015.
 */
@Component
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception ignored) {
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getBooks() {
        List<Book> res;

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            res = em.createQuery("from Book").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Book deleted = em.find(Book.class, book.getId());
            em.remove(deleted);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Book getBook(UUID id) {
        Book res;

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            res = (Book) em.createQuery("from Book b where b.id = :id").setParameter("id", id).getSingleResult();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return res;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
