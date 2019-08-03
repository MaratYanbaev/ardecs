package com.ardecs.services;

import com.ardecs.car_configurator.entities.Model;
import com.ardecs.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    public Model getModel(Long id) {
        return modelRepository
                .findById(id)
                .get();
    }

    public Model save(Model model) {
        return modelRepository.save(model);
    }

    public void updateModel(Model model) {
        modelRepository.updateDataOfModelById(
                model.getName(),
                model.getPrice(),
                model.getId()
        );
    }

    public void deleteByModel(Model model) {
        modelRepository.delete(model);
    }

    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }
}
