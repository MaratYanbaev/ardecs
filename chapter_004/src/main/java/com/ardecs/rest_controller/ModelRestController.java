package com.ardecs.rest_controller;

import com.ardecs.car_configurator.entities.Brand;
import com.ardecs.car_configurator.entities.Model;
import com.ardecs.myException.InvalidInputException;
import com.ardecs.myException.ResourceNotFoundException;
import com.ardecs.services.BrandService;
import com.ardecs.services.ComplectationService;
import com.ardecs.services.ModelComplectationService;
import com.ardecs.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 29.07.2019
 */
@RestController
@RequestMapping(value = "/restModel/", headers = {"Accept=application/json"})
public class ModelRestController {

    @Autowired
    ModelService modelService;
    @Autowired
    BrandService brandService;
    @Autowired
    ComplectationService complectationService;
    @Autowired
    ModelComplectationService modelComplectationService;

    @GetMapping(value = "{brandId}")
    public Set<Model> getModels(@PathVariable("brandId") Long brandId) throws ResourceNotFoundException {
        Brand brand = brandService.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id :: " + brandId));
        return brand.getModelSet();
    }

    @GetMapping(value = "model/{modelId}")
    public ResponseEntity<Model> getModel(@PathVariable("modelId") Long modelId) throws ResourceNotFoundException {
        Model model = modelService.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Model not found for this id :: " + modelId));
        return ResponseEntity.ok().body(model);
    }

    @PutMapping("{modelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeData(
            @Valid Model model,
            BindingResult bindingResult,
            @PathVariable("modelId") Long modelId
    ) throws ResourceNotFoundException, InvalidInputException {
        if (bindingResult.hasErrors()) {
            throwException(bindingResult);
        }
        modelService.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Model is updated, not found for this id :: " + modelId));
        modelService.updateModel(model);
    }

    @PostMapping("{brandId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Model createModel(
            @Valid Model model,
            BindingResult bindingResult,
            @PathVariable("brandId") Long brandId
    ) throws ResourceNotFoundException, InvalidInputException {
        if (bindingResult.hasErrors()) {
            throwException(bindingResult);
        }
        Brand brand = brandService.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id :: " + brandId));
        model.setBrand(brand);
        return modelService.save(model);
    }

    @DeleteMapping("{modelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModel(@PathVariable("modelId") Long modelId) throws ResourceNotFoundException {
        Model model = modelService.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Model not found for this id :: " + modelId));
        modelService.deleteByModel(model);
    }

    private void throwException(BindingResult bindingResult) throws InvalidInputException {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String errors = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" "));
        throw new InvalidInputException(errors);
    }
}
