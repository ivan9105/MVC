package com.springapp.mvc.controller;

import com.springapp.mvc.jms.pojo.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Иван on 20.11.2016.
 */
@Controller
@RequestMapping("/")
public class JmsController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "testJms", method = RequestMethod.GET)
    public String testJms(Model model) {
        try {
            jmsTemplate.convertAndSend("mailbox", new MessageResponse("from", "to"));
        } catch (Exception e) {
            model.addAttribute("error", e.toString());
        }
        return "jms/jmsPage";
    }
}
