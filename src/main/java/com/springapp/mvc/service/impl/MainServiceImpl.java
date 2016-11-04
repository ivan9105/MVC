package com.springapp.mvc.service.impl;

import com.springapp.mvc.data.AuthorRepository;
import com.springapp.mvc.model.Author;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.MainService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by Иван on 19.10.2016.
 */
@Transactional
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private ServletContext servletContext;

    @Override
    public void generateTestData() {
        authorRepository.deleteAll();
        String result = readFile();
        Map<String, Set<String>> authorsMap = getData(result);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            Random random = new Random();

            for (Map.Entry<String, Set<String>> entry : authorsMap.entrySet()) {
                Author author = createAuthor(em, entry);
                createBooks(em, random, entry, author);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private Map<String, Set<String>> getData(String result) {
        String[] rows = result.split("\\$");
        Map<String, Set<String>> authorsMap = new HashMap<>();
        for (String row : rows) {
            String[] arr = row.split("#");
            if (arr.length != 2) {
                throw new RuntimeException(String.format("Row '%s' has bad format", Arrays.toString(arr)));
            }
            if (authorsMap.get(arr[1]) == null) {
                authorsMap.put(arr[1], new HashSet<String>());
            }
            authorsMap.get(arr[1]).add(arr[0]);
        }
        return authorsMap;
    }

    private String readFile() {
        String result = StringUtils.EMPTY;
        ClassLoader classLoader = servletContext.getClassLoader();
        if (classLoader == null) {
            throw new RuntimeException("It's impossible");
        }
        URL resource = classLoader.getResource("test_data/books.txt");
        if (resource == null) {
            throw new RuntimeException("file not found");
        }

        File file = new File(resource.getFile());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fileInputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            result = IOUtils.toString(br);
        } catch (IOException ignore) {
        }
        return result;
    }

    private void createBooks(EntityManager em, Random random, Map.Entry<String, Set<String>> entry, Author author) {
        Set<String> bookNames = entry.getValue();
        for (String bookName : bookNames) {
            Book book = new Book();
            book.setName(bookName);
            book.setYear(random.nextInt((2016 - 1970) + 1) + 1970);
            book.setAuthor(author);
            em.persist(book);
        }
    }

    private Author createAuthor(EntityManager em, Map.Entry<String, Set<String>> entry) {
        String authorName = entry.getKey();
        String[] authorArr = authorName.split(" ");
        Author author = new Author();
        if (authorArr.length == 3) {
            author.setName(authorArr[0]);
            author.setMiddleName(authorArr[1]);
            author.setLastName(authorArr[2]);
        } else if (authorArr.length == 2) {
            author.setName(authorArr[0]);
            author.setLastName(authorArr[1]);
        } else if (authorArr.length == 1) {
            author.setName(authorArr[0]);
        } else {
            throw new RuntimeException(String.format("Author '%s' has bad format", authorName));
        }
        em.persist(author);
        return author;
    }
}
