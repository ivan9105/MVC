package com.springapp.mvc.context;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * Created by Иван on 18.12.2016.
 */
public class SpringContextHelper {
    private ApplicationContext context;

    public SpringContextHelper(ServletContext servletContext) {
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    public Object getBean(final String beanRef) {
        return context.getBean(beanRef);
    }

    public Object getBean(Class beanClass) {
        return context.getBean(beanClass);
    }
}
