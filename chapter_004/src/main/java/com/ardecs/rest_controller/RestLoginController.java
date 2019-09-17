package com.ardecs.rest_controller;

import com.ardecs.entities.entityForSecurity.User;
import com.ardecs.jwtToken.JwtTokenProvider;
import com.ardecs.myException.InvalidInputException;
import com.ardecs.repositories.UserRepository;
import com.ardecs.services.CustomUserDetailsService;
import com.ardecs.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 11.08.2019
 */
@RestController
@RequestMapping(value = "/restLogin")
public class RestLoginController {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity login(@RequestBody User user) {
        String username = user.getName();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
        String token = jwtTokenProvider.createToken(username, Set.copyOf(userRepository.findByName(username).getRoles()));
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return ok(model);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity createNewUser(
            @RequestBody @Valid User user,
            BindingResult bindingResult
    ) throws InvalidInputException {
        String password;
        try {
            if (userDetailsService.loadUserByUsername(user.getName()) != null) {
                bindingResult
                        .rejectValue("name", "error.user",
                                "A user registered with this name provided already exist.");
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errors = fieldErrors.stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(" "));
            throw new InvalidInputException(errors);
        } else {
            password = user.getPassword();
            userDetailsService.save(user);
        }

        user.setPassword(password);
        return ok(user);
    }
}
