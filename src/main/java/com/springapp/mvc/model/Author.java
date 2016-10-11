package com.springapp.mvc.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Иван on 11.10.2016.
 */
@Entity
@Table(name = "test_authors")
public class Author extends StandardEntity {
    @NotNull()
    @Size(min=3, max=50)
    @Column(name="name")
    private String name;

    @NotNull()
    @Size(min=3, max=50)
    @Column(name="middleName")
    private String middleName;

    @Size(min=3, max=50)
    @Column(name="lastName")
    private String lastName;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "author")
    protected List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
