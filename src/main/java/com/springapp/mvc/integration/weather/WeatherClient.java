package com.springapp.mvc.integration.weather;

import com.springapp.mvc.integration.weather.schema.GetCitiesByCountry;
import com.springapp.mvc.integration.weather.schema.GetCitiesByCountryResponse;
import com.springapp.mvc.integration.weather.schema.ObjectFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by Иван on 01.09.2015.
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
}
