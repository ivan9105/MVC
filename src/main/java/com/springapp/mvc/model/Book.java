package com.springapp.mvc.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Иван on 29.07.2015.
 */
@Entity
@Table(name = "test_books")
public class Book implements Serializable{
    @Id
    @Type(type="uuid-char")
    @Column(name="id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name = "year")
    private Integer year;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
}
