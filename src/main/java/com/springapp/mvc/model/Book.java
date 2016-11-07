package com.springapp.mvc.model;

import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.bridge.builtin.IntegerBridge;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ���� on 29.07.2015.
 */
@Indexed
@Entity
@Table(name = "test_books")
@Analyzer(impl = RussianAnalyzer.class)
public class Book extends StandardEntity {
    @NotNull()
    @Size(min=3, max=50)
    @Column(name="name")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    protected String name;

    @NotNull()
    @Min(value = 1970)
    @Max(value = 2100)
    @Column(name = "year")
    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    @FieldBridge(impl = IntegerBridge.class)
    protected Integer year;

    @NotNull()
    @ManyToOne(fetch = FetchType.EAGER)
    @IndexedEmbedded
    @JoinColumn(name = "author_id")
    protected Author author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
