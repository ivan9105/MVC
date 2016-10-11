package com.springapp.mvc.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * Created by Иван on 11.10.2016.
 */
public class ServletUtil {
    public static String getRemoteAddr(HttpServletRequest request) {
        String res = request.getRemoteAddr();
        if (res.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                res = inetAddress.getHostAddress();
            } catch (Exception e) {
                res = request.getLocalAddr();
            }
        }
        return res;
    }
}
