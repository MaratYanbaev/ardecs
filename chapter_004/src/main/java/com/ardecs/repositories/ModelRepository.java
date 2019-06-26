package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("select m from Model m where m.name = ?1")
    Model findByName(String name);

    @Transactional
    @Modifying
    @Query("update Model m set m.name = :name, m.price = :price where m.id = :id")
    void updateDataOfModelById(
            @Param("name") String name,
            @Param("price") Integer price,
            @Param("id") Long id
    );
}
