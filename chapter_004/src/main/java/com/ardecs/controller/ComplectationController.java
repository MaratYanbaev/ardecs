package com.ardecs.controller;

import com.ardecs.services.ComplectationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 16.06.2019
 */

@Controller
@RequestMapping("/complect/")
public class ComplectationController {

    @Autowired
    ComplectationService complectationService;
}
