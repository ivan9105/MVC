package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Иван on 15.11.2016.
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping(value = "security", method = RequestMethod.GET)
    public String securiryPage() {
        return "security/securityMain";
    }

    @RequestMapping(value = "logout.done", method = RequestMethod.GET)
    public String logoutDone() {
        return "redirect:/security";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "security/login";
    }

    @RequestMapping(value = "protected", method = RequestMethod.GET)
    public String protectedPage(Model model) {
        model.addAttribute("title", "Spring Security 3.2.0");
        return "security/protected";
    }

    @RequestMapping(value = "confidential", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("title", "Spring Security 3.2.0");
        return "security/protected";
    }
}
