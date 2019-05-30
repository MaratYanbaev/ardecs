package com.ardecs.repositories;

import com.ardecs.car_configurator.ColorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends CrudRepository<ColorEntity, Long> {
}
