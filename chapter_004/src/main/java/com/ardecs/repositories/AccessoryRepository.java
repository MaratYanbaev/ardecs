package com.ardecs.repositories;

import com.ardecs.entities.mainEntities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 11.07.2019
 */
@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
}
