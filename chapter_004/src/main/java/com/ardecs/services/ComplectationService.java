package com.ardecs.services;

import com.ardecs.entities.mainEntities.Complectation;
import com.ardecs.repositories.ComplectationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Complectation> getAllComplectation() {
        return complectationRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public Complectation save(Complectation complectation) {
        return complectationRepository.save(complectation);
    }
}
