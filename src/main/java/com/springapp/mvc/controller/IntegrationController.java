package com.springapp.mvc.controller;

import com.springapp.mvc.integration.weather.WeatherClient;
import com.springapp.mvc.integration.weather.schema.GetCitiesByCountryResponse;
import com.springapp.mvc.integration.weather.schema.GetWeatherResponse;
import com.springapp.mvc.integration.weather.schema.country.NewDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import java.util.Locale;
import java.util.Map;

/**
 * Created by ivan on 05.09.2015.
 */
@Controller
@RequestMapping(value = IntegrationController.ROOT_PATH)
public class IntegrationController {
    public static final String ROOT_PATH = "/api/weather";

    public static final String COUNTRY_NAME_LAT = "Russia";
    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private Jaxb2Marshaller countryMarshaller;

    @Autowired
    private Jaxb2Marshaller responseMarshaller;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "weather", method = RequestMethod.GET)
    public ModelAndView getCity() {
        ModelAndView view = new ModelAndView("integration/weather/cityPage");
        GetCitiesByCountryResponse response = null;
        try {
            response = weatherClient.getCitiesByCountry(COUNTRY_NAME_LAT);
        } catch (Exception e) {
            view.addObject("error", e.getMessage());
            return view;
        }
        Map<String, String> data = getData(response);
        view.addObject("data", data);
        view.addObject("table", new NewDataSet.Table());
        return view;
    }

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public String getMessages() {
        return "localization/messages";
    }

    @RequestMapping(value = "weather", method = RequestMethod.POST)
    public String getWeather(@ModelAttribute("table") NewDataSet.Table table, Model model) {
        GetWeatherResponse response = weatherClient.getWeatherByCity(table.getCity(), COUNTRY_NAME_LAT);
        model.addAttribute("weather", getWeatherResponse(response));
        return "integration/weather/weatherPage";
    }

    private String getWeatherResponse(GetWeatherResponse response) {
        String resultStr = response.getGetWeatherResult();
        try {
            return responseMarshaller.unmarshal(new StreamSource(new StringReader(resultStr))).toString();
        } catch (Exception e) {
            if (resultStr.equals("Data Not Found")) {
                return messageSource.getMessage("globalweather.dataNotFound", new String[]{}, new Locale("en"));
            } else {
                return messageSource.getMessage("globalweather.parseResponseError", new String[]{}, new Locale("en"));
            }
        }
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
