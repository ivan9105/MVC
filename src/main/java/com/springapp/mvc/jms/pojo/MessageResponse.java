package com.springapp.mvc.jms.pojo;

import java.io.Serializable;

/**
 * Created by Иван on 20.11.2016.
 */
public class MessageResponse implements Serializable{
    private String from;
    private String to;

    public MessageResponse() {
    }

    public MessageResponse(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
