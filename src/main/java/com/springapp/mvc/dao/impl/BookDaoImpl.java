package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.BookDao;
import com.springapp.mvc.model.Book;
import org.hibernate.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
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

    @Override
    public void indexBooks() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            try {
                fullTextEm.createIndexer(Book.class).startAndWait();
            } catch (InterruptedException ignore) {
                System.out.print("ERROR");
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public long getCount(String searchText) {
        long res = 0;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            QueryBuilder qb = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
            org.apache.lucene.search.Query q = qb.keyword().wildcard().onFields("name", "year", "author.name",
                    "author.middleName", "author.lastName").matching("*" + searchText.toLowerCase() + "*").createQuery();
            FullTextQuery fullTextQuery = fullTextEm.createFullTextQuery(q, Book.class);
            res = fullTextQuery.getResultSize();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return res;
    }

    @Override
    public List<Book> searchForBook(String searchText, Pageable pageRequest) {
        List<Book> res = null;

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            QueryBuilder qb = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
            org.apache.lucene.search.Query q = qb.keyword().wildcard().onFields("name", "year", "author.name",
                    "author.middleName", "author.lastName").matching("*" + searchText.toLowerCase() + "*").createQuery();
            FullTextQuery fullTextQuery = fullTextEm.createFullTextQuery(q, Book.class);
            int page = pageRequest.getPageNumber();
            int size = pageRequest.getPageSize();
            fullTextQuery.setFirstResult(page * size);
            fullTextQuery.setMaxResults(size);
            List list = fullTextQuery.getResultList();
            res = new ArrayList<>();
            for (Object object : list) {
                res.add((Book) object);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return res;
    }

    @Override
    public List<Book> searchForBook(String searchText) {
        List<Book> res = null;

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            QueryBuilder qb = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
            org.apache.lucene.search.Query q = qb.keyword().onFields("name", "year", "author").matching(searchText).createQuery();
            FullTextQuery fullTextQuery = fullTextEm.createFullTextQuery(q, Book.class);
            List list = fullTextQuery.getResultList();
            res = new ArrayList<>();
            for (Object object : list) {
                res.add((Book) object);
            }
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
