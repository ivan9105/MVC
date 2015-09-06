package com.springapp.mvc.controller;

import com.springapp.mvc.integration.weather.WeatherClient;
import com.springapp.mvc.integration.weather.schema.GetCitiesByCountryResponse;
import com.springapp.mvc.integration.weather.schema.country.NewDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.StringReader;

/**
 * Created by ���� on 05.09.2015.
 */
@Controller
@RequestMapping("/")
public class IntegrationController {
    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private Jaxb2Marshaller countryMarshaller;

    @RequestMapping(value = "weather", method = RequestMethod.GET)
    public String getBooks(Model model) {
        GetCitiesByCountryResponse response = weatherClient.getCitiesByCountry("Russia");
        String resultStr = response.getGetCitiesByCountryResult();
        NewDataSet dataSet = (NewDataSet) countryMarshaller.unmarshal(new StreamSource(new StringReader(resultStr)));
        model.addAttribute("weatherResponse", resultStr);
        return "integration/weather/weatherPage";
    }
}
