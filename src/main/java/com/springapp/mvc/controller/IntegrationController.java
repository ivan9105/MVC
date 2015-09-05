package com.springapp.mvc.controller;

import com.springapp.mvc.integration.weather.WeatherClient;
import com.springapp.mvc.integration.weather.schema.GetCitiesByCountryResponse;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Иван on 05.09.2015.
 */
@Controller
@RequestMapping("/")
public class IntegrationController {
    @Autowired
    private WeatherClient weatherClient;

    @RequestMapping(value = "weather", method = RequestMethod.GET)
    public String getBooks(Model model) {
        GetCitiesByCountryResponse response = weatherClient.getCitiesByCountry("Russia");
        model.addAttribute("weatherResponse", response.getGetCitiesByCountryResult());
        return "integration/weather/weatherPage";
    }
}
