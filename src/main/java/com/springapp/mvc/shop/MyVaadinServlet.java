package com.springapp.mvc.shop;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Иван on 13.12.2016.
 */
@SpringUI(path = "main")
@WebServlet(urlPatterns = {"/vaadin/*", "/VAADIN/*"}, asyncSupported = true)
@VaadinServletConfiguration(
        productionMode = false,
        ui = MyUI.class)
public class MyVaadinServlet extends SpringVaadinServlet {
}
