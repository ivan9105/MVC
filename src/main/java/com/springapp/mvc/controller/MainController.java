package com.springapp.mvc.controller;

import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
    private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Main page");
		return "index";
	}

	@RequestMapping(value = "books", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		return "books";
	}

	@RequestMapping(value = "books/add", method = RequestMethod.GET)
	public String getAddBook(Model model) {
		model.addAttribute("bookAttribute", new Book());
		return "addBook";
	}

	@RequestMapping(value = "books/add", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("bookAttribute") Book book) {
		bookService.saveBook(book);
		return "addedBook";
	}

	@RequestMapping(value = "books/delete", method = RequestMethod.GET)
	public String deleteBook(@RequestParam(value="id", required=true) UUID id, Model model) {
		bookService.deleteBook(bookService.getBook(id));
		model.addAttribute("id", id);
		return "deletedBook";
	}

	@RequestMapping(value = "books/edit", method = RequestMethod.GET)
	public String editBook(@RequestParam(value="id", required=true) UUID id, Model model) {
		model.addAttribute("bookAttribute", bookService.getBook(id));
		return "editBook";
	}

	@RequestMapping(value = "books/edit", method = RequestMethod.POST)
	public String saveEdit(
			@ModelAttribute("bookAttribute") Book book,
			@RequestParam(value = "id", required = true) UUID id,
			Model model) {
		bookService.updateBook(book);
		model.addAttribute("id", id);
		return "editedBook";
	}
}