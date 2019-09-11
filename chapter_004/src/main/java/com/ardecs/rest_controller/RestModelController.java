package com.ardecs.rest_controller;

import com.ardecs.entities.mainEntities.Brand;
import com.ardecs.entities.mainEntities.Model;
import com.ardecs.myException.DuplicateNameOfModelException;
import com.ardecs.myException.InvalidInputException;
import com.ardecs.myException.ResourceNotFoundException;
import com.ardecs.services.BrandService;
import com.ardecs.services.ComplectationService;
import com.ardecs.services.ModelComplectationService;
import com.ardecs.services.ModelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 29.07.2019
 */
@RestController
@RequestMapping("/restModel")
public class RestModelController {

    @Autowired
    ModelService modelService;
    @Autowired
    BrandService brandService;
    @Autowired
    ComplectationService complectationService;
    @Autowired
    ModelComplectationService modelComplectationService;

    @ApiOperation(value = "List of models certain Brand by id", response = Brand.class)
    @ApiResponse(code = 200, message = "Successfully retrieved Brand")
    @GetMapping("/models/{brandId}")
    public Brand getModels(@PathVariable("brandId") Long brandId) throws ResourceNotFoundException {
        return brandService.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id :: " + brandId));
    }

    @ApiOperation(value = "Get model by id", response = Model.class)
    @GetMapping("/model/{modelId}")
    public Model getModel(@PathVariable("modelId") Long modelId) throws ResourceNotFoundException {
        return modelService.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Model not found for this id :: " + modelId));
    }

    @ApiOperation(value = "Update a model")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeData(
            @ApiParam(value = "Model update in database", required = true)
            @RequestBody @Valid Model model,
            BindingResult bindingResult
    ) throws ResourceNotFoundException, InvalidInputException {
        if (bindingResult.hasErrors()) {
            throwInvalidInputException(bindingResult);
        }
        Long modelId = model.getId();
        modelService.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Model needs to update, not found for this id :: " + modelId));
        modelService.updateModel(model);
    }

    @ApiOperation(value = "Create new model")
    @PostMapping("/{brandId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createModel(
            @ApiParam(value = "Model stored in database", required = true)
            @RequestBody @Valid Model model,
            BindingResult bindingResult,
            @ApiParam(value = "Brand id that contains new model", required = true)
            @PathVariable("brandId") Long brandId
    ) throws ResourceNotFoundException, InvalidInputException, DuplicateNameOfModelException {
        if (bindingResult.hasErrors()) {
            throwInvalidInputException(bindingResult);
        }
        if (modelService.findByName(model.getName())) {
            throw new DuplicateNameOfModelException(model.getName() + " already exist, please change one!");
        }
        Brand brand = brandService.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id :: " + brandId));
        model.setBrand(brand);
        Model m = modelService.save(model);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/restModel/model/" + m.getId()));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a model")
    @DeleteMapping("/{modelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModel(@PathVariable("modelId") Long modelId) throws ResourceNotFoundException {
        Model model = modelService.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Model not found for this id :: " + modelId));
        modelService.deleteByModel(model);
    }

    private void throwInvalidInputException(BindingResult bindingResult) throws InvalidInputException {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String errors = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" "));
        throw new InvalidInputException(errors);
    }
}
