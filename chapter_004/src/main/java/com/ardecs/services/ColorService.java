package com.ardecs.services;

import com.ardecs.entities.mainEntities.Color;
import com.ardecs.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 11.07.2019
 */
@Service
public class ColorService {

    @Autowired
    ColorRepository colorRepository;

    public List<Color> findAll() {
        return colorRepository.findAll();
    }
}
