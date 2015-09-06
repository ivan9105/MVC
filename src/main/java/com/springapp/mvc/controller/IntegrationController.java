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
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public ModelAndView getBooks() {
        GetCitiesByCountryResponse response = weatherClient.getCitiesByCountry("Russia");
        Map<String, String> data = getData(response);
        ModelAndView view = new ModelAndView("integration/weather/weatherPage");
        view.addObject("data", data);
        view.addObject("table", new NewDataSet.Table());
        return view;
    }

    private Map<String, String> getData(GetCitiesByCountryResponse response) {
        String resultStr = response.getGetCitiesByCountryResult();
        NewDataSet dataSet = (NewDataSet) countryMarshaller.unmarshal(new StreamSource(new StringReader(resultStr)));

        Map<String, String> data = new HashMap<String, String>();
        for (NewDataSet.Table item : dataSet.getTable()) {
            data.put(item.getCity(), item.getCity());
        }
        return data;
    }
}
