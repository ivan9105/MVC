package com.springapp.mvc.jms;

import com.springapp.mvc.jms.pojo.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * Created by Иван on 20.11.2016.
 */
@Component
public class MessageReceiver {
    private static final String ORDER_RESPONSE_QUEUE = "mailbox";

    @Value("${host}")
    private String host;

    @Value("${port}")
    private String port;

    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
    public void receiveMessage(final Message<MessageResponse> message) throws JMSException, InterruptedException {
        MessageResponse messageResponse = message.getPayload();
        System.out.println("Received <" + messageResponse + ">");
    }
}
