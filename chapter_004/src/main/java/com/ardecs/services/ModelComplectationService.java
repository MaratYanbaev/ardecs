package com.ardecs.services;

import com.ardecs.entities.compositeId.ModelCompId;
import com.ardecs.entities.mainEntities.Complectation;
import com.ardecs.entities.mainEntities.ModelComplectation;
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
        Complectation complectation = complectationRepository.findByName(name);
        ModelComplectation entity = new ModelComplectation();
        entity.setId(new ModelCompId(modelId, complectation.getId()));
        entity.setPrice(price);
        entity.setModel(modelRepository.findById(modelId).get());
        entity.setComplectation(complectation);
        modelComplectationRepository.save(entity);
    }

    public void delete(Long modelId, Long compId) {
        modelComplectationRepository.delete(modelId, compId);
    }

    public boolean existComplectation(Long modelId, String complectation) {
        Long compId = complectationRepository.findByName(complectation).getId();
        return modelComplectationRepository.existComplectation(modelId, compId) == null;
    }
}
