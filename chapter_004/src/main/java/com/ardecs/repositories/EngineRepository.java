package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

    @Override
    List<Engine> findAll();
}
