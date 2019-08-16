package com.ardecs.myAccessDeniedHandler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 14.07.2019
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        RequestDispatcher dispatcher;
        String requestedUri = request.getRequestURI();
        if (requestedUri.contains("rest")) {
            dispatcher = request.getRequestDispatcher("/myRestError");
        } else {
            dispatcher = request.getRequestDispatcher("/myError");
        }
        dispatcher.forward(request, response);
    }
}
