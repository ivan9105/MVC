package com.springapp.mvc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Иван on 10.10.2016.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.springapp.mvc.data")
public class WebConfig {
}
