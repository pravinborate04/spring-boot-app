package com.borate.pravin.pim.config;

import com.borate.pravin.pim.helper.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Pravin Borate
 * 14/03/21
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Constants {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HashMap<String, Object> exceptionMessage = new HashMap<String, Object>();
        exceptionMessage.put(MESSAGE, authException.getMessage());
        exceptionMessage.put(CODE, HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(CONTENT_TYPE);
        response.getWriter().print(objectMapper.writeValueAsString(exceptionMessage));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
