package com.ardecs.controller;

import com.ardecs.car_configurator.entityOfSecurity.Role;
import com.ardecs.car_configurator.entityOfSecurity.User;
import com.ardecs.services.RoleService;
import com.ardecs.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 05.07.2019
 */
@Slf4j
@Controller
@RequestMapping
public class UserController {

    @Autowired
    UserService userDetailsService;

    @Autowired
    RoleService roleService;

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        List<Role> roles = roleService.getRoles();
        modelAndView.addObject("authorities", roles);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(
            @Valid User user,
            BindingResult bindingResult,
            @RequestParam(name = "role") String[] role) {
        ModelAndView modelAndView = new ModelAndView();
        UserDetails userExists = userDetailsService.loadUserByUsername(user.getName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("name", "error.user",
                            "A user registered with this name provided already exist.");
        }
        if (bindingResult.hasErrors()) {
            List<Role> roles = roleService.getRoles();
            modelAndView.addObject("authorities", roles);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("registration");
        } else {
            userDetailsService.saveUser(user, role);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }
}
