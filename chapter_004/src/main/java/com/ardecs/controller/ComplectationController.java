package com.ardecs.controller;

import com.ardecs.car_configurator.entities.Complectation;
import com.ardecs.service.ComplectationService;
import com.ardecs.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 16.06.2019
 */

@Slf4j
@Controller
@RequestMapping("/complect/")
public class ComplectationController {

    @Autowired
    ComplectationService complectationService;
    @Autowired
    ModelService modelService;

    @GetMapping("new/{modelId}/{brandId}")
    public String addComp(@PathVariable(value = "modelId") Long modelId,
                          @PathVariable(value = "brandId") Long brandId,
                          Model model) {
        List<Complectation> complectations = complectationService.getCompByIdOfBrand(brandId);
        model.addAttribute("complectations", complectations);
        model.addAttribute("modelId", modelId);
        model.addAttribute("brandId", brandId);
        return "addComplect";
    }
}
