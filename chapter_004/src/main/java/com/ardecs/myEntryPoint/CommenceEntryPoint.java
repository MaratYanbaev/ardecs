//package com.ardecs.myEntryPoint;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Enumeration;
//
///**
// * @author Marat Yanbaev (yanbaevms@gmail.com)
// * @since 03.08.2019
// */
//@Component
//public class CommenceEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException authException
//    ) throws IOException{
//        if (!(request.getRequestURI().contains("brand"))) {
//            Enumeration<String> auth = request.getHeaderNames();
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//        } else {
//            new DefaultRedirectStrategy().sendRedirect(request, response, "/login");
//        }
//    }
//
//}
