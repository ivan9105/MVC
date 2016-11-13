package com.springapp.mvc.controller;

import com.springapp.mvc.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Иван on 15.10.2016.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private MainService mainService;

    @RequestMapping(value = "crudMain", method = RequestMethod.GET)
    public String getMainCRUDPage() {
        return "crud/crudMain";
    }

    @RequestMapping(value = "generateTestData", method = RequestMethod.GET)
    public String generateTestDate() {
        mainService.generateTestData();
        return "crud/crudMain";
    }

    @RequestMapping(value = "security", method = RequestMethod.GET)
    public String securiryPage() {
        return "security/securityMain";
    }

    //Todo remove it, make security sample
    //Todo customize login page, and 403 page
    @RequestMapping(value = "protected", method = RequestMethod.GET)
    public String protectedPage(Model model) {
        //Todo information about security
        model.addAttribute("title", "Spring Security 3.2.0");
        return "security/protected";
    }

    @RequestMapping(value = "confidential", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("title", "Spring Security 3.2.0");
        return "security/protected";
    }
}
