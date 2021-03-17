package com.borate.pravin.pim.config;

import com.borate.pravin.pim.helper.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class to handler Access Denied requests
 *
 * @author Pravin Borate
 * 14/03/21
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler, Constants {
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
        }
        HashMap<String, Object> exceptionMessage = new HashMap<String, Object>();
        exceptionMessage.put(MESSAGE, accessDeniedException.getMessage());
        exceptionMessage.put(CODE, HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(CONTENT_TYPE);
        response.getWriter().print(objectMapper.writeValueAsString(exceptionMessage));
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

}
