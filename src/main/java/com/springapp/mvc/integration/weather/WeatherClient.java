package com.springapp.mvc.integration.weather;

import com.springapp.mvc.integration.weather.schema.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by ���� on 01.09.2015.
 */
public class WeatherClient extends WebServiceGatewaySupport{
    private static Log log = LogFactory.getLog(WeatherClient.class);

    private static ObjectFactory factory;
    static {
        factory = new ObjectFactory();
    }

    public GetCitiesByCountryResponse getCitiesByCountry(String countryNameLat) {
        GetCitiesByCountry request = factory.createGetCitiesByCountry();
        request.setCountryName(countryNameLat);
        log.debug(String.format("Request forecast for: %s", countryNameLat));

        return (GetCitiesByCountryResponse) getWebServiceTemplate().
                marshalSendAndReceive(request, new SoapActionCallback("http://www.webserviceX.NET/GetCitiesByCountry"));
    }

    public GetWeatherResponse getWeatherByCity(String cityName, String countryNameLat) {
        GetWeather request = factory.createGetWeather();
        request.setCityName(cityName);
        request.setCountryName(countryNameLat);
        log.debug(String.format("Request forecat for: %s, %s", countryNameLat, cityName));

        return (GetWeatherResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request, new SoapActionCallback("http://www.webserviceX.NET/GetWeather"));
    }
}
