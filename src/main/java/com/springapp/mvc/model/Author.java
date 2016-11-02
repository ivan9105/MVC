package com.springapp.mvc.model;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Иван on 11.10.2016.
 */
@Indexed
@Entity
@Table(name = "test_authors")
@Analyzer(impl = RussianAnalyzer.class)
public class Author extends StandardEntity {
    @NotNull()
    @Size(min = 3, max = 50)
    @Column(name = "name")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String name;

    @Size(min = 3, max = 50)
    @Column(name = "middleName")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String middleName;

    @Size(min = 3, max = 50)
    @Column(name = "lastName")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ContainedIn
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

    public String getInstanceName() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(name)) {
            if (middleName != null && lastName != null) {
                sb.append(name.substring(0, 1).toUpperCase()).append(".");
            } else {
                sb.append(name).append(" ");
            }
        }
        if (StringUtils.isNotEmpty(middleName)) {
            if (name != null && lastName != null) {
                sb.append(middleName.substring(0, 1).toUpperCase()).append(".");
            } else {
                sb.append(name).append(" ");
            }
        }
        if (StringUtils.isNotEmpty(lastName)) {
            if (sb.length() == 0) {
                sb.append(lastName);
            } else {
                sb.append(" ").append(lastName);
            }
        }
        return sb.toString();
    }
}
