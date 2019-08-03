package com.ardecs.services;

import com.ardecs.car_configurator.entities.Brand;
import com.ardecs.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 21.06.2019
 */
@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}
