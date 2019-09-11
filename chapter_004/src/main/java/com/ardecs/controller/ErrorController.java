package com.ardecs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 14.07.2019
 */
@Controller
@RequestMapping("/myError")
public class ErrorController {

    @DeleteMapping
    public String methodDeleteNotAllowed(Model model, HttpServletRequest request) {
        String url = getPath(request);
        model.addAttribute("url", url);
        model.addAttribute("message", "You are not authorized to DELETE data!");
        return "forbiddenPage";
    }

    @PutMapping
    public String methodPutNotAllowed(Model model, HttpServletRequest request) {
        String url = getPath(request);
        model.addAttribute("url", url);
        model.addAttribute("message", "You are not authorized to CHANGE data!");
        return "forbiddenPage";
    }

    @PostMapping
    public String methodPostNotAllowed(Model model, HttpServletRequest request) {
        String url = getPath(request);
        model.addAttribute("url", url);
        model.addAttribute("message", "You are not authorized to ADD data!");
        return "forbiddenPage";
    }

    private String getPath(HttpServletRequest request) {
        String url = request.getHeader("referer");
        try {
            url = new URL(url).getPath();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        return url;
    }
}
