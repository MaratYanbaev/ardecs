package com.ardecs.services;

import com.ardecs.car_configurator.entityOfSecurity.Role;
import com.ardecs.car_configurator.entityOfSecurity.User;
import com.ardecs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 02.07.2019
 */
@Service
public class CustomUserDetailsService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthorities(user));
        }
        return null;
    }

    public User saveUser(User user, String[] roles) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.stream(roles).map(Role::new).collect(Collectors.toList()));
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<String> list = new ArrayList<>();
        for (Role role : user.getRoles()) {
            String name = "ROLE_" + role.getName();
            list.add(name);
        }
        String[] userRoles = list.toArray(new String[0]);
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
