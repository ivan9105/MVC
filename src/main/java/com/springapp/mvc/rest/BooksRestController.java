package com.springapp.mvc.rest;

import com.springapp.mvc.data.BookPagingRepository;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.rest.dto.BookDto;
import com.springapp.mvc.rest.dto.LinkDto;
import com.springapp.mvc.rest.response.BooksResponse;
import com.springapp.mvc.util.ServletUtil;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Иван on 10.10.2016.
 */
@RequestMapping(value = BooksRestController.ROOT_PATH)
@RestController
public class BooksRestController {
    public static final String ROOT_PATH = "/api/books";

    @Autowired
    private BookPagingRepository bookRepository;

    @RequestMapping(value = "books", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public BooksResponse getBooks(HttpServletRequest request) {
        BooksResponse res = new BooksResponse();
        res.setBooks(new ArrayList<BookDto>());
        Iterable<Book> find = bookRepository.findAll();
        for (Book book : find) {
            BookDto bookDto = getBookDto(book, request);
            res.getBooks().add(bookDto);
        }
        return res;
    }

    @RequestMapping(value = "book", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public BooksResponse getBook(@RequestParam(value = "id", required = true) String idStr, HttpServletRequest request) {
        BooksResponse res = new BooksResponse();
        res.setBooks(new ArrayList<BookDto>());

        UUID id = parseId(idStr);
        if (id == null) {
            throw new IllegalStateException("Id has invalid format");
        }

        Book find = bookRepository.findOne(id);
        if (find != null) {
            res.getBooks().add(getBookDto(find, request));
        }
        return res;
    }

    private BookDto getBookDto(Book book, HttpServletRequest request) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setYear(book.getYear());
        bookDto.setId(book.getId().toString());
        LinkDto linkDto = new LinkDto();
        linkDto.setRel("self");
        linkDto.setHref(ServletUtil.getRemoteAddr(request) + "/api/book?id=" + bookDto.getId());
        bookDto.setLinkDto(linkDto);
        return bookDto;
    }

    @Nullable
    private UUID parseId(@Nullable String id) {
        if (id == null) {
            return null;
        }

        try {
            return UUID.fromString(id);
        } catch (Exception e) {
            return null;
        }
    }
}
