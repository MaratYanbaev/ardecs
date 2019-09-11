package com.ardecs.controller;

import com.ardecs.entities.mainEntities.*;
import com.ardecs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 27.06.2019
 */

@Controller
@RequestMapping("/modComp/")
public class ModCompController {

    @Autowired
    private ComplectationService complectationService;
    @Autowired
    private ModelComplectationService modelComplectationService;
    @Autowired
    private AccessoryModComService accessoryModComService;
    @Autowired
    private EngineModComService engineModComService;
    @Autowired
    private ColorModComService colorModComService;
    @Autowired
    private AccessoryService accessoryService;
    @Autowired
    private EngineService engineService;
    @Autowired
    private ColorService colorService;

    private Long modelId;
    private Long brandId;

    @GetMapping("{modelId}/{brandId}")
    public String showComplectations(
            @PathVariable(value = "modelId") Long modelId,
            @PathVariable(value = "brandId") Long brandId,
            Model model
    ) {
        this.modelId = modelId;
        this.brandId = brandId;
        List<Complectation> complectations = complectationService.getCompByIdOfModel(modelId);
        ListIterator<Integer> prices = modelComplectationService.getPriceByIdOfModel(modelId).listIterator();
        model.addAttribute("complectations", complectations);
        model.addAttribute("prices", prices);
        model.addAttribute("brandId", brandId);
        return "complect";
    }

    @GetMapping
    public String addComplect(Model model) {
        addComplect(model, new com.ardecs.entities.mainEntities.Model());
        return "addComplect";
    }

    @GetMapping("allStuff/{compId}")
    public String getAllInfoOfModel(@PathVariable(value = "compId") Long compId, Model model) {
        setAllInfoOfModel(new AccessoryModCom(), new EngineModCom(), new ColorModCom(), model, compId);
        return "equipment";
    }

    @PostMapping
    public String addCompToModel(
            @Valid com.ardecs.entities.mainEntities.Model myModel,
            BindingResult bindingResult,
            Model model
    ) {
        if (!(modelComplectationService.existComplectation(modelId, myModel.getName()))) {
            bindingResult
                    .rejectValue(
                            "name",
                            "error.myModel",
                            "This model already contains the same complectation"
                    );
        }
        if (bindingResult.hasErrors()) {
            addComplect(model, myModel);
            return "addComplect";
        } else {
            modelComplectationService.addComplect(myModel.getName(), myModel.getPrice(), modelId);
            return "redirect:/modComp/" + modelId + "/" + brandId;
        }
    }

    @DeleteMapping("{compId}")
    public String deleteCompFromModel(@PathVariable(value = "compId") Long compId) {
        modelComplectationService.delete(modelId, compId);
        return "redirect:/modComp/" + modelId + "/" + brandId;
    }

    @DeleteMapping("accessory")
    public String deleteAccessory(@RequestParam(value = "accessorId") Long accessId,
                                  @RequestParam(value = "compId") Long compId) {
        accessoryModComService.deleteAccessory(accessId, modelId, compId);
        return "redirect:/modComp/allStuff/" + compId;
    }

    @DeleteMapping("engine")
    public String deleteEngine(@RequestParam(value = "engineId") Long engineId,
                               @RequestParam(value = "compId") Long compId) {
        engineModComService.deleteEngine(engineId, modelId, compId);
        return "redirect:/modComp/allStuff/" + compId;
    }

    @DeleteMapping("color")
    public String deleteColor(@RequestParam(value = "colorId") Long colorId,
                              @RequestParam(value = "compId") Long compId) {
        colorModComService.deleteColor(colorId, modelId, compId);
        return "redirect:/modComp/allStuff/" + compId;
    }

    @PostMapping("accessory")
    public String addAccessory(
            @Valid AccessoryModCom accessoryModCom,
            BindingResult bindingResult,
            Model model,
            @RequestParam(value = "accessorId") Long accessorId,
            @RequestParam(value = "compId") Long compId
    ) {
        if (bindingResult.hasErrors()) {
            setAllInfoOfModel(accessoryModCom, new EngineModCom(), new ColorModCom(), model, compId);
            return "equipment";
        } else {
            accessoryModComService.addAccessory(accessorId, modelId, compId, accessoryModCom.getPrice());
            return "redirect:/modComp/allStuff/" + compId;
        }
    }

    @PostMapping("engine")
    public String addEngine(
            @Valid EngineModCom engineModCom,
            BindingResult bindingResult,
            Model model,
            @RequestParam(value = "engineId") Long engineId,
            @RequestParam(value = "compId") Long compId
    ) {
        if (bindingResult.hasErrors()) {
            setAllInfoOfModel(new AccessoryModCom(), engineModCom, new ColorModCom(), model, compId);
            return "equipment";
        } else {
            engineModComService.addEngine(engineId, modelId, compId, engineModCom.getPrice());
            return "redirect:/modComp/allStuff/" + compId;
        }
    }

    @PostMapping("color")
    public String addColor(
            @Valid ColorModCom colorModCom,
            BindingResult bindingResult,
            Model model,
            @RequestParam(value = "colorId") Long colorId,
            @RequestParam(value = "compId") Long compId
    ) {
        if (bindingResult.hasErrors()) {
            setAllInfoOfModel(new AccessoryModCom(), new EngineModCom(), colorModCom, model, compId);
            return "equipment";
        } else {
            colorModComService.addColor(colorId, modelId, compId, colorModCom.getPrice());
            return "redirect:/modComp/allStuff/" + compId;
        }
    }

    private void setAllInfoOfModel(
            AccessoryModCom accessoryModCom,
            EngineModCom engineModCom,
            ColorModCom colorModCom,
            Model model,
            Long compId
    ) {
        List<AccessoryModCom> accessoryModComList = accessoryModComService.getAccessOfModCom(modelId, compId);
        model.addAttribute("accessoryModComList", accessoryModComList);
        model.addAttribute("accessoryModCom", accessoryModCom);
        List<EngineModCom> engineModComList = engineModComService.getEngineOfModCom(modelId, compId);
        model.addAttribute("engineModComList", engineModComList);
        model.addAttribute("engineModCom", engineModCom);
        List<ColorModCom> colorModComList = colorModComService.getColorOfModCom(modelId, compId);
        model.addAttribute("colorModComList", colorModComList);
        model.addAttribute("colorModCom", colorModCom);
        List<Accessory> accessories = accessoryService.getAllAccessory();
        model.addAttribute("accessories", accessories);
        List<Engine> engines = engineService.findAll();
        model.addAttribute("engines", engines);
        List<Color> colors = colorService.findAll();
        model.addAttribute("colors", colors);
        model.addAttribute("compId", compId);
        model.addAttribute("modelId", modelId);
        model.addAttribute("brandId", brandId);
    }

    private void addComplect(Model model, com.ardecs.entities.mainEntities.Model myModel) {
        List<Complectation> complectations = complectationService.getAllComplectation();
        model.addAttribute("complectations", complectations);
        model.addAttribute("model", myModel);
        model.addAttribute("modelId", modelId);
        model.addAttribute("brandId", brandId);
    }
}
