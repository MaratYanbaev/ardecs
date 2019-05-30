package com.ardecs.repositories;

import com.ardecs.car_configurator.AccessoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends CrudRepository<AccessoryEntity, Long> {
}
