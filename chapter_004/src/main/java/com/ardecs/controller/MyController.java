package com.ardecs.controller;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MyController {

    @GetMapping("/addNewModel")
    public String addNewModel(@RequestParam(name = "model", defaultValue = "Toyota") String model
            , @RequestParam(value = "price", defaultValue = "1000000") int price
            , Map<String, Object> mod) {
        mod.put("model", model);
        mod.put("price", price);
        return "addNewModel";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("some", "Hello");
        return "main";
    }
}