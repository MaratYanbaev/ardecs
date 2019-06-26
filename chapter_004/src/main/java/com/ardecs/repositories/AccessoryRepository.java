package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.Accessory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends CrudRepository<Accessory, Long> {
}
