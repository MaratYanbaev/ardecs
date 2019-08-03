package com.ardecs.services;

import com.ardecs.car_configurator.compositeId.ModelCompId;
import com.ardecs.car_configurator.entities.ModelComplectation;
import com.ardecs.repositories.ComplectationRepository;
import com.ardecs.repositories.ModelComplectationRepository;
import com.ardecs.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 26.06.2019
 */
@Service
public class ModelComplectationService {

    @Autowired
    ModelComplectationRepository modelComplectationRepository;
    @Autowired
    ComplectationRepository complectationRepository;
    @Autowired
    ModelRepository modelRepository;


    public List<Integer> getPriceByIdOfModel(Long id) {
        return modelComplectationRepository.getPriceByIdOfModel(id);
    }

    public void addComplect(String name, int price, Long modelId) {
        Long compId = complectationRepository.findIdByNameOfComplect(name);
        ModelComplectation entity = new ModelComplectation();
        entity.setId(new ModelCompId(modelId, compId));
        entity.setPrice(price);
        entity.setModel(modelRepository.findById(modelId).get());
        entity.setComplectation(complectationRepository.findById(compId).get());
        modelComplectationRepository.save(entity);
    }

    public void delete(Long modelId, Long compId) {
        modelComplectationRepository.delete(modelId, compId);
    }

    public boolean existComplectation(Long modelId, String complectation) {
        Long compId = complectationRepository.findIdByNameOfComplect(complectation);
        ModelComplectation modelComplectation = modelComplectationRepository.existComplectation(modelId, compId);
        return modelComplectationRepository.existComplectation(modelId, compId) == null;
    }
}
