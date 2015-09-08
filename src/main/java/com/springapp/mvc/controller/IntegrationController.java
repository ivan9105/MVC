package com.springapp.mvc.controller;

import com.springapp.mvc.integration.weather.WeatherClient;
import com.springapp.mvc.integration.weather.schema.GetCitiesByCountryResponse;
import com.springapp.mvc.integration.weather.schema.GetWeatherResponse;
import com.springapp.mvc.integration.weather.schema.country.NewDataSet;
import com.springapp.mvc.integration.weather.schema.response.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ���� on 05.09.2015.
 */
@Controller
@RequestMapping("/")
public class IntegrationController {
    public static final String COUNTRY_NAME_LAT = "Russia";
    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private Jaxb2Marshaller countryMarshaller;

    @Autowired
    private Jaxb2Marshaller responseMarshaller;

    @RequestMapping(value = "weather", method = RequestMethod.GET)
    public ModelAndView getCity() {
        GetCitiesByCountryResponse response = weatherClient.getCitiesByCountry(COUNTRY_NAME_LAT);
        Map<String, String> data = getData(response);
        ModelAndView view = new ModelAndView("integration/weather/cityPage");
        view.addObject("data", data);
        view.addObject("table", new NewDataSet.Table());
        return view;
    }

    @RequestMapping(value = "weather", method = RequestMethod.POST)
    public String getWeather(@ModelAttribute("table") NewDataSet.Table table, Model model) {
        GetWeatherResponse response = weatherClient.getWeatherByCity(table.getCity(), COUNTRY_NAME_LAT);
        model.addAttribute("weather", getResponse(response));
        return "integration/weather/weatherPage";
    }

    private String getResponse(GetWeatherResponse response) {
        String resultStr = response.getGetWeatherResult();
        CurrentWeather weather = (CurrentWeather)
                responseMarshaller.unmarshal(new StreamSource(new StringReader(resultStr)));

        return weather.toString();
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
