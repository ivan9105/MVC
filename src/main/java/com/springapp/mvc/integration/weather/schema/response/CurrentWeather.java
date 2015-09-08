
package com.springapp.mvc.integration.weather.schema.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Wind" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Visibility" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SkyConditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Temperature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DewPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RelativeHumidity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pressure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "location",
    "time",
    "wind",
    "visibility",
    "skyConditions",
    "temperature",
    "dewPoint",
    "relativeHumidity",
    "pressure",
    "status"
})
@XmlRootElement(name = "CurrentWeather")
public class CurrentWeather {

    @XmlElement(name = "Location", required = true)
    protected String location;
    @XmlElement(name = "Time", required = true)
    protected String time;
    @XmlElement(name = "Wind", required = true)
    protected String wind;
    @XmlElement(name = "Visibility", required = true)
    protected String visibility;
    @XmlElement(name = "SkyConditions", required = true)
    protected String skyConditions;
    @XmlElement(name = "Temperature", required = true)
    protected String temperature;
    @XmlElement(name = "DewPoint", required = true)
    protected String dewPoint;
    @XmlElement(name = "RelativeHumidity", required = true)
    protected String relativeHumidity;
    @XmlElement(name = "Pressure", required = true)
    protected String pressure;
    @XmlElement(name = "Status", required = true)
    protected String status;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the wind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWind() {
        return wind;
    }

    /**
     * Sets the value of the wind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWind(String value) {
        this.wind = value;
    }

    /**
     * Gets the value of the visibility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Sets the value of the visibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisibility(String value) {
        this.visibility = value;
    }

    /**
     * Gets the value of the skyConditions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkyConditions() {
        return skyConditions;
    }

    /**
     * Sets the value of the skyConditions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkyConditions(String value) {
        this.skyConditions = value;
    }

    /**
     * Gets the value of the temperature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Sets the value of the temperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemperature(String value) {
        this.temperature = value;
    }

    /**
     * Gets the value of the dewPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDewPoint() {
        return dewPoint;
    }

    /**
     * Sets the value of the dewPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDewPoint(String value) {
        this.dewPoint = value;
    }

    /**
     * Gets the value of the relativeHumidity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    /**
     * Sets the value of the relativeHumidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelativeHumidity(String value) {
        this.relativeHumidity = value;
    }

    /**
     * Gets the value of the pressure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * Sets the value of the pressure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPressure(String value) {
        this.pressure = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", wind='" + wind + '\'' +
                ", visibility='" + visibility + '\'' +
                ", skyConditions='" + skyConditions + '\'' +
                ", temperature='" + temperature + '\'' +
                ", dewPoint='" + dewPoint + '\'' +
                ", relativeHumidity='" + relativeHumidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
