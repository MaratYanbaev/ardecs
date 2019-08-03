package com.ardecs.repositories;

import com.ardecs.car_configurator.entityOfSecurity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    @Override
    <S extends User> S save(S entity);
}
