package com.ardecs.repositories;

import com.ardecs.car_configurator.ModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<ModelEntity, Long> {

    @Query("select m from ModelEntity m where m.model = ?1")
    ModelEntity findByModel(String model);

    List<ModelEntity> findByPriceBetween(Integer firstPrice, Integer secondPrice);
}
