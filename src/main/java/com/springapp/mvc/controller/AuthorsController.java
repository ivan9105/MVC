package com.springapp.mvc.controller;

import com.google.common.collect.Lists;
import com.springapp.mvc.data.AuthorRepository;
import com.springapp.mvc.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getAuthors(Model model) {
        List<Author> authors = Lists.newArrayList(authorRepository.findAll());
        model.addAttribute("authors", authors);
        return "authors/authors";
    }

    @RequestMapping(value = "authors/add", method = RequestMethod.GET)
    public String getAddAuthor(Model model) {
        model.addAttribute("authorAttribute", new Author());
        return "authors/addAuthor";
    }

    @RequestMapping(value = "authors/add", method = RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute("authorAttribute") Author author,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "authors/addAuthor";
        }
        authorRepository.save(author);
        return "authors/authors";
    }

    @RequestMapping(value = "authors/delete", method = RequestMethod.GET)
    public String deleteAuthor(@RequestParam(value = "id", required = true) UUID id) {
        authorRepository.delete(id);
        return "authors/authors";
    }

    @RequestMapping(value = "authors/edit", method = RequestMethod.GET)
    public String editAuthor(@RequestParam(value = "id", required = true) UUID id, Model model) {
        model.addAttribute("authorAttribute", authorRepository.findOne(id));
        return "authors/editAuthor";
    }

    @RequestMapping(value = "authors/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("authorAttribute") Author author,
                           BindingResult result) {
        validator.validate(author, result);
        if (result.hasErrors()) {
            return "authors/editAuthor";
        }
        authorRepository.save(author);
        return "authors/authors";
    }
}
