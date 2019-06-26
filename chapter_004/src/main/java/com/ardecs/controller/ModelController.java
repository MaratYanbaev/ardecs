package com.ardecs.controller;

import com.ardecs.car_configurator.entities.Complectation;
import com.ardecs.car_configurator.entities.Model;
import com.ardecs.service.BrandService;
import com.ardecs.service.ComplectationService;
import com.ardecs.service.ModelComplectationService;
import com.ardecs.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListIterator;
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
        Set<Model> modelEntities = modelService.getModels(brandId);
        model.addAttribute("modelEntityList", modelEntities);
        model.addAttribute("brandId", brandId);
        return "model";
    }

    @GetMapping("{modelId}/{brandId}")
    public String showComplectations(@PathVariable(value = "modelId") Long modelId,
                                     @PathVariable(value = "brandId") Long brandId,
                                     org.springframework.ui.Model model) {
        List<Complectation> complectations = complectationService.getCompByIdOfModel(modelId);
        ListIterator<Integer> prices = modelComplectationService.getPriceByIdOfModel(modelId).listIterator();
        model.addAttribute("complectations", complectations);
        model.addAttribute("prices", prices);
        model.addAttribute("modelId", modelId);
        model.addAttribute("brandId", brandId);
        return "complect";
    }

    @GetMapping("new/{brandId}")
    public String addModel(@PathVariable("brandId") Long brandId, org.springframework.ui.Model model) {
        model.addAttribute("model", new Model());
        model.addAttribute("brandId", brandId);

        return "newModel";
    }

    @PostMapping("new/{brandId}")
    public String saveModel(@PathVariable("brandId") Long brandId, Model model) {
//        if (errors.hasErrors()) {
//            return null;//Вернуть страницу на, которой не правильно заполнили данные
//        }
        model.setBrand(brandService.getBrand(brandId));
//        model.setBrandId(model.getBrandId());
        modelService.save(model);
        return "redirect:/model/" + brandId;
    }

    @PostMapping("{modelId}/{brandId}")
    public String addCompToModel(@PathVariable(value = "modelId") Long modelId,
                                 @PathVariable(value = "brandId") Long brandId,
                                 @RequestParam(value = "price") int price,
                                 @RequestParam(value = "name") String name) {
        modelComplectationService.addComplect(name, price, modelId);
        return "redirect:/model/" + modelId + "/" + brandId;
    }

    @PutMapping("{id}")
    public String updateModel(@PathVariable("id") Long modelId, org.springframework.ui.Model model) {
        Model modelEntity = modelService.getModel(modelId);
        model.addAttribute("modelEntity", modelEntity);

        return "updateModel";
    }

    @PutMapping("change")
    public String changeData(Model model) {
        modelService.updateModel(model);

        return "redirect:" + model.getBrand().getId();
    }

    @DeleteMapping("{id}")
    public String deleteModel(@PathVariable("id") Long modelId) {
        Long brandId = modelService.getModel(modelId).getBrand().getId();
        modelService.deleteModel(modelId);
        return "redirect:/model/" + brandId;
    }
}
