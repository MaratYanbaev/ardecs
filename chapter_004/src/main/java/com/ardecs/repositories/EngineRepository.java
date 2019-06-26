package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.Complectation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends CrudRepository<Complectation, Long> {
}
