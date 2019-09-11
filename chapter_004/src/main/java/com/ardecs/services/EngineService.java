package com.ardecs.services;

import com.ardecs.entities.mainEntities.Engine;
import com.ardecs.repositories.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 11.07.2019
 */
@Service
public class EngineService {

    @Autowired
    EngineRepository engineRepository;

    public List<Engine> findAll() {
        return engineRepository.findAll();
    }
}
