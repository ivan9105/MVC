package com.springapp.mvc.controller;

import com.google.common.collect.Lists;
import com.springapp.mvc.data.AuthorRepository;
import com.springapp.mvc.dto.PageableInfo;
import com.springapp.mvc.model.Author;
import com.springapp.mvc.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created by Иван on 12.10.2016.
 */
@Controller
@RequestMapping("/")
public class AuthorsController {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @RequestMapping(value = "authors", method = RequestMethod.GET)
    public String getAuthors(@RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "page", required = false) Integer page,
                             Model model) {
        long count = authorRepository.count();
        if (size == null || size == 0) {
            size = 10;
        }
        if (page == null) {
            page = 1;
        }
        PageableInfo pageableInfo = getPageableInfo(size, page, count);
        List<Author> authors = authorRepository.findAll(new PageRequest(page - 1, size)).getContent();
        model.addAttribute("authors", authors);
        model.addAttribute("pageableInfo", pageableInfo);
        return "authors/authors";
    }

    private PageableInfo getPageableInfo(Integer size, Integer page, double count) {
        int pageCount = (int) Math.ceil(count / size);
        PageableInfo pageableInfo = new PageableInfo();
        pageableInfo.setSize(size);
        pageableInfo.setPage(page);
        pageableInfo.setLastValue(pageCount);
        pageableInfo.setCurrentValue(page);
        return pageableInfo;
    }

    @RequestMapping(value = "authors/add", method = RequestMethod.GET)
    public String getAddAuthor(Model model) {
        model.addAttribute("authorAttribute", new Author());
        return "authors/addAuthor";
    }

    @RequestMapping(value = "authors/add", method = RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute("authorAttribute") Author author,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "authors/addAuthor";
        }
        authorRepository.save(author);
        model.addAttribute("authors", Lists.newArrayList(authorRepository.findAll()));
        return "authors/authors";
    }

    @RequestMapping(value = "authors/delete", method = RequestMethod.GET)
    public String deleteAuthor(@RequestParam(value = "id", required = true) UUID id, Model model) {
        authorRepository.delete(id);
        PageableInfo pageableInfo = getPageableInfo(10, 1, authorRepository.count());
        List<Author> authors = authorRepository.findAll(new PageRequest(0, 10)).getContent();
        model.addAttribute("authors", authors);
        model.addAttribute("pageableInfo", pageableInfo);
        return "authors/authors";
    }

    @RequestMapping(value = "authors/edit", method = RequestMethod.GET)
    public String editAuthor(@RequestParam(value = "id", required = true) UUID id, Model model) {
        model.addAttribute("authorAttribute", authorRepository.findOne(id));
        return "authors/editAuthor";
    }

    @RequestMapping(value = "authors/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("authorAttribute") Author author,
                           BindingResult result, Model model) {
        validator.validate(author, result);
        if (result.hasErrors()) {
            return "authors/editAuthor";
        }
        authorRepository.save(author);
        PageableInfo pageableInfo = getPageableInfo(10, 1, authorRepository.count());
        List<Author> authors = authorRepository.findAll(new PageRequest(0, 10)).getContent();
        model.addAttribute("authors", authors);
        model.addAttribute("pageableInfo", pageableInfo);
        return "authors/authors";
    }
}
