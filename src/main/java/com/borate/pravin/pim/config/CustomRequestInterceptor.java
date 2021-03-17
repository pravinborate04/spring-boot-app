package com.borate.pravin.pim.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

/**
 * This interceptor to log each request and calculate time takem
 *
 * @author Pravin Borate
 * 13/03/21
 */
@Component
public class CustomRequestInterceptor implements HandlerInterceptor {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED || response.getStatus() == HttpServletResponse.SC_FORBIDDEN
                || response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            return true;
        }
        long startTime = Instant.now().toEpochMilli();
        log.info("Start " + request.getMethod() + " URL:" + request.getRequestURL().toString() +
                ":: Time=" + Instant.now());
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED || response.getStatus() == HttpServletResponse.SC_FORBIDDEN
                || response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            return;
        }
        long startTime = (Long) request.getAttribute("startTime");
        Instant endTime = Instant.now();
        long timeTake = endTime.toEpochMilli() - startTime;
        log.info("End " + request.getMethod() + " URL:" + request.getRequestURL().toString() +
                ":: Time=" + endTime + " Time Taken = " + (timeTake) + "ms" + (ex == null ? "" : "EXCEPTION ::" + ExceptionUtils.getStackTrace(ex) + "::"));
    }
}
