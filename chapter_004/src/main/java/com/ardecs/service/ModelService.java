package com.ardecs.service;

import com.ardecs.car_configurator.entities.Brand;
import com.ardecs.car_configurator.entities.Model;
import com.ardecs.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 21.06.2019
 */
@Service
public class ModelService {

    @Autowired
    ModelRepository modelRepository;
    @Autowired
    BrandService brandService;

    public Model getModel(Long id) {
        return modelRepository
                .findById(id)
                .get();
    }

    public Set<Model> getModels(Long id) {
        Brand brand = brandService.getBrand(id);
        return brand.getModelSet();
    }

    public void save(Model model) {
        modelRepository.save(model);
    }

    public void updateModel(Model model) {
        modelRepository.updateDataOfModelById(
                model.getName(),
                model.getPrice(),
                model.getId()
        );
    }

    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }
}
