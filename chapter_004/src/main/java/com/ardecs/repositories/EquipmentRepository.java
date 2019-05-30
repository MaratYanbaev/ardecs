package com.ardecs.repositories;

import com.ardecs.car_configurator.EquipmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends CrudRepository<EquipmentEntity, Long> {
}
