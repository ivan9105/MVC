package com.springapp.mvc.jmx;

import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

import javax.management.Notification;

/**
 * Created by Иван on 28.11.2016.
 */
public class JmxSamplerBean implements JmxSamplerMBean, NotificationPublisherAware {
    private NotificationPublisher publisher;
    private int sequenceNumber;

    @Override
    public void echo(String message) {
        Notification notification = new Notification(String.format("Echo: %s", message), this, 0,
                String.format("Id:%s", sequenceNumber));
        notification.setSequenceNumber(sequenceNumber++);
        notification.setUserData(message.hashCode());
        publisher.sendNotification(notification);

        System.out.println(String.format("Echo: %s", message));
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher publisher) {
        this.publisher = publisher;
    }
}
