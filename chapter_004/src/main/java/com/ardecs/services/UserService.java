package com.ardecs.services;

import com.ardecs.car_configurator.entityOfSecurity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User saveUser(User user, String[] roles);
}
