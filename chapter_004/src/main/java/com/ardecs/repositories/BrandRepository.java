package com.ardecs.repositories;

import com.ardecs.car_configurator.BrandEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {

    @Query("select b from BrandEntity b where b.brandId = ?1")
    BrandEntity findId(Long id);
}
