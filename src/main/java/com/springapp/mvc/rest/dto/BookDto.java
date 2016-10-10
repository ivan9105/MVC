package com.springapp.mvc.rest.dto;

/**
 * Created by Иван on 10.10.2016.
 */
public class BookDto extends BaseDto{
    private String id;
    private String name;
    private Integer year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
