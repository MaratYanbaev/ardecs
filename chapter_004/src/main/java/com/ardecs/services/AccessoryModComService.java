package com.ardecs.services;

import com.ardecs.entities.mainEntities.AccessoryModCom;
import com.ardecs.repositories.AccessoryModComRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 27.06.2019
 */
@Service
public class AccessoryModComService {

    @Autowired
    AccessoryModComRepository accessoryModComRepository;

    public List<AccessoryModCom> getAccessOfModCom(Long modelId, Long compId) {
        return accessoryModComRepository.getAccessOfModCom(modelId, compId);
    }

    public void deleteAccessory(Long accessId, Long modelId, Long compId) {
        accessoryModComRepository.delete(accessId, modelId, compId);
    }

    public void addAccessory(Long accessId, Long modelId, Long compId, int price) {
        accessoryModComRepository.addAccessory(accessId, modelId, compId, price);
    }
}
