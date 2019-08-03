package com.ardecs.repositories;

import com.ardecs.car_configurator.entityOfSecurity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Override
    List<Role> findAll();
}
