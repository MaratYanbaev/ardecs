package com.ardecs.controller;

import com.ardecs.car_configurator.entities.Model;
import com.ardecs.services.BrandService;
import com.ardecs.services.ComplectationService;
import com.ardecs.services.ModelComplectationService;
import com.ardecs.services.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 07.06.2019
 */

@Slf4j
@Controller
@RequestMapping("/model/")
public class ModelController {

    @Autowired
    ModelService modelService;
    @Autowired
    BrandService brandService;
    @Autowired
    ComplectationService complectationService;
    @Autowired
    ModelComplectationService modelComplectationService;

    @GetMapping("{brandId}")
    public String showModels(@PathVariable("brandId") Long brandId, org.springframework.ui.Model model) {
        Set<Model> modelEntities = brandService.findById(brandId).get().getModelSet();
        model.addAttribute("modelEntityList", modelEntities);
        model.addAttribute("brandId", brandId);
        return "model";
    }

    @GetMapping("new/{brandId}")
    public String addModel(@PathVariable("brandId") Long brandId, org.springframework.ui.Model model) {
        model.addAttribute("model", new Model());
        model.addAttribute("brandId", brandId);

        return "newModel";
    }

    @GetMapping("renewed/{id}")
    public String updateModel(@PathVariable("id") Long modelId, org.springframework.ui.Model model) {
        Model modelEntity = modelService.findById(modelId).get();
        model.addAttribute("model", modelEntity);

        return "changeModel";
    }

    @PutMapping("renewed")
    public String changeData(
            @Valid Model model,
            BindingResult bindingResult,
            org.springframework.ui.Model uiModel
    ) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("model", model);
            return "changeModel";
        } else {
            modelService.updateModel(model);
            return "redirect:" + model.getBrand().getId();
        }
    }

    @PostMapping("new/{brandId}")
    public String createModel(
            @Valid Model model,
            BindingResult bindingResult,
            org.springframework.ui.Model uiModel,
            @PathVariable("brandId") Long brandId
    ) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("model", model);
            uiModel.addAttribute("brandId", brandId);
            return "newModel";
        } else {
            model.setBrand(brandService.findById(brandId).get());
            modelService.save(model);
            return "redirect:/model/" + brandId;
        }
    }

    @DeleteMapping("{id}")
    public String deleteModel(@PathVariable("id") Long modelId) {
        Long brandId = modelService.findById(modelId).get().getBrand().getId();
        modelService.deleteModelById(modelId);
        return "redirect:/model/" + brandId;
    }
}
