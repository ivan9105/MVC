package com.springapp.mvc.model.shop;

import com.springapp.mvc.model.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Иван on 27.12.2016.
 */
@Entity
@Table(name = "SHOP_USER")
public class User extends StandardEntity {
    @Column(name = "NAME_")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "MIDDLENAME")
    private String middleName;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
