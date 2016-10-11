package com.springapp.mvc.rest.dto;

/**
 * Created by Иван on 11.10.2016.
 */
public class LinkDto {
    private String rel;
    private String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
