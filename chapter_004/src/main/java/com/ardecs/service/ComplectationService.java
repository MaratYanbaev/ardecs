package com.ardecs.service;

import com.ardecs.car_configurator.entities.Complectation;
import com.ardecs.repositories.ComplectationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 21.06.2019
 */
@Service
public class ComplectationService {

    @Autowired
    ComplectationRepository complectationRepository;

    public List<Complectation> getCompByIdOfModel(Long id) {
        return complectationRepository.getCompByIdOfModel(id);
    }

    public List<Complectation> getCompByIdOfBrand(Long brandId) {
        return complectationRepository.getCompByIdOfBrand(brandId);
    }

    public Long findCompIdByNameOfComplect(String name) {
        return complectationRepository.findCompIdByNameOfComplect(name);
    }

    public Complectation save(Complectation complectation) {
        return complectationRepository.save(complectation);
    }
}
