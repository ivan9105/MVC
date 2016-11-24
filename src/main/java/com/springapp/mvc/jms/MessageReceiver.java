package com.springapp.mvc.jms;

import com.springapp.mvc.jms.pojo.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * Created by Иван on 20.11.2016.
 */
@Component
public class MessageReceiver {
    private static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

    private static final String ORDER_RESPONSE_QUEUE = "mailbox";

    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
    public void receiveMessage(final Message<MessageResponse> message) throws JMSException {
        MessageResponse messageResponse = message.getPayload();
        System.out.println("Received <" + messageResponse + ">");
    }
}
