package com.springapp.mvc.service;

import org.springframework.stereotype.Component;

/**
 * Created by Иван on 30.12.2016.
 */
@Component
public interface ShopLoginService {
    boolean checkLogin(String login, String password);
}
