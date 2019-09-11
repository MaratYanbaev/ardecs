package com.ardecs.services;

import com.ardecs.entities.entityForSecurity.Role;
import com.ardecs.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 10.07.2019
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
