package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 11.07.2019
 */
@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    @Override
    List<Accessory> findAll();
}
