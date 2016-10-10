package com.springapp.mvc.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapp.mvc.rest.dto.BookDto;

import java.util.List;

/**
 * Created by Иван on 10.10.2016.
 */
public class BooksResponse {
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookDto> books;

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
