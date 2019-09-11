package com.ardecs.myEntryPoint;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 03.08.2019
 */
@Component
public class CommenceEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException{
        String requestedUri = request.getRequestURI();
        if (requestedUri.contains("rest")) {
            if (!(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value())) { // checks token is expired or not
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid name/password supplied or You are not registered!");
            }
        } else {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/login");
        }
    }

}
