package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Override
    List<Color> findAll();
}
