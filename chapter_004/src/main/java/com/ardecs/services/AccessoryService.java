package com.ardecs.services;

import com.ardecs.car_configurator.entities.Accessory;
import com.ardecs.repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 11.07.2019
 */
@Service
public class AccessoryService {

    @Autowired
    AccessoryRepository accessoryRepository;

    public List<Accessory> getAllAccessory() {
        return accessoryRepository.findAll();
    }
}
