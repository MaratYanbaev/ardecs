package com.ardecs.services;

import com.ardecs.car_configurator.entities.ColorModCom;
import com.ardecs.repositories.ColorModComRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 27.06.2019
 */
@Service
public class ColorModComService {

    @Autowired
    ColorModComRepository colorModComRepository;

    public List<ColorModCom> getColorOfModCom(Long modelId, Long compId) {
        return colorModComRepository.getColorOfModCom(modelId, compId);
    }

    public void addColor(Long colorId, Long modelId, Long compId, int price) {
        colorModComRepository.addColor(colorId, modelId, compId, price);
    }

    public void deleteColor(Long colorId, Long modelId, Long compId) {
        colorModComRepository.delete(colorId, modelId, compId);
    }
}
