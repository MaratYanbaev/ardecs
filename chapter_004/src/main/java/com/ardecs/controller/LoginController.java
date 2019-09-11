package com.ardecs.controller;

import com.ardecs.entities.entityForSecurity.Role;
import com.ardecs.entities.entityForSecurity.User;
import com.ardecs.services.CustomUserDetailsService;
import com.ardecs.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 05.07.2019
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    RoleService roleService;

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        return setUpRegPage(new ModelAndView(), new User());
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(
            @Valid User user,
            BindingResult bindingResult,
            @RequestParam(name = "role") String[] role) {
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
            return setUpRegPage(new ModelAndView(), user);
        } else {
            List<Role> roles = Arrays.stream(role).map(Role::new).collect(Collectors.toList());
            user.setRoles(roles);
            userDetailsService.save(user);
            return setUpRegPage(new ModelAndView().addObject("successMessage", "User has been registered successfully"), new User());
        }
    }

    private ModelAndView setUpRegPage(ModelAndView modelAndView, User user) {
        List<Role> roles = roleService.getRoles();
        modelAndView.addObject("authorities", roles);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
}
