package com.springapp.mvc.controller;

import com.springapp.mvc.jms.pojo.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Иван on 20.11.2016.
 */
@Controller
@RequestMapping("/")
public class JmsController {
//    @Autowired
//    private MessageSender messageSender;

//    @Autowired
//    private BrokerServiceBean broker;
    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "testJms", method = RequestMethod.GET)
    public String testJms() {
        jmsTemplate.convertAndSend("mailbox", new MessageResponse("from", "to"));
//        broker.start();
//        messageSender.sendMessage(new MessageRequest("client", "server"));
//        broker.stop();
        return "/index";
    }
}
