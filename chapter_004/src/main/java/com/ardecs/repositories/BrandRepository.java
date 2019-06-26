package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("select b from Brand b where b.id = ?1")
    Brand findId(Long id);
}
