package com.ardecs.services;

import com.ardecs.car_configurator.entities.EngineModCom;
import com.ardecs.repositories.EngineModComRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 27.06.2019
 */
@Service
public class EngineModComService {

    @Autowired
    EngineModComRepository engineModComRepository;

    public List<EngineModCom> getEngineOfModCom(Long modelId, Long compId) {
        return engineModComRepository.getEngineOfModCom(modelId, compId);
    }

    public void addEngine(Long engineId, Long modelId, Long compId, int price) {
        engineModComRepository.addEngine(engineId, modelId, compId, price);
    }

    public void deleteEngine(Long engineId, Long modelId, Long compId) {
        engineModComRepository.delete(engineId, modelId, compId);
    }
}
