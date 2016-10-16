package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Иван on 15.10.2016.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping(value = "crudMain", method = RequestMethod.GET)
    public String getMainCRUDPage() {
        return "crud/crudMain";
    }
}
