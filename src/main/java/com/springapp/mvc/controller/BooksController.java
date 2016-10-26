package com.springapp.mvc.controller;

import com.google.common.collect.Lists;
import com.springapp.mvc.data.AuthorRepository;
import com.springapp.mvc.dto.PageableInfo;
import com.springapp.mvc.model.Author;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class BooksController {
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm";

    @Autowired
    private BookService bookService;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Spring samples");
        return "index";
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String getBooks(@RequestParam(value = "size", required = false) Integer size,
                           @RequestParam(value = "page", required = false) Integer page,
                           Model model) {
        long count = bookService.getCount();
        if (size == null || size == 0) {
            size = 10;
        }
        if (page == null) {
            page = 1;
        }
        int pageCount = (int) Math.ceil((double) count / size);
        PageableInfo pageableInfo = new PageableInfo();
        pageableInfo.setSize(size);
        pageableInfo.setPage(page);
        pageableInfo.setLastValue(pageCount);
        pageableInfo.setCurrentValue(page);

        List<Book> books = bookService.getBooks(new PageRequest(page - 1, size));
        for (Book book : books) {
            if (book.getName() != null && book.getName().length() > 50) {
                book.setName(book.getName().substring(0, 47) + "...");
            }
        }
        model.addAttribute("books", books);
        model.addAttribute("pageableInfo", pageableInfo);
        return "books/books";
    }

    @RequestMapping(value = "books/add", method = RequestMethod.GET)
    public String getAddBook(Model model) {
        model.addAttribute("bookAttribute", new Book());
        setAuthorList(model);
        return "books/addBook";
    }

    private void setAuthorList(Model model) {
        ArrayList<Author> authors = Lists.newArrayList(authorRepository.findAll());
        model.addAttribute("authorList", authors);
    }

    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("bookAttribute") Book book,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            setAuthorList(model);
            return "books/addBook";
        }
        bookService.saveBook(book);
        model.addAttribute("date", new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date()));
        return "books/addedBook";
    }

    @RequestMapping(value = "books/delete", method = RequestMethod.GET)
    public String deleteBook(@RequestParam(value = "id", required = true) UUID id, Model model) {
        bookService.deleteBook(bookService.getBook(id));
        model.addAttribute("id", id);
        model.addAttribute("date", new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date()));
        return "books/deletedBook";
    }

    @RequestMapping(value = "books/edit", method = RequestMethod.GET)
    public String editBook(@RequestParam(value = "id", required = true) UUID id, Model model) {
        model.addAttribute("bookAttribute", bookService.getBook(id));
        setAuthorList(model);
        return "books/editBook";
    }

    @RequestMapping(value = "books/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("bookAttribute") Book book,
                           @RequestParam(value = "id", required = true) UUID id,
                           BindingResult result, Model model) {
        validator.validate(book, result);

        if (result.hasErrors()) {
            setAuthorList(model);
            return "books/editBook";
        }

        bookService.updateBook(book);
        model.addAttribute("id", id);
        model.addAttribute("date", new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date()));
        return "books/editedBook";
    }
}