package com.ardecs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 17.04.2019
 */

@Configuration
public class UserConfiguration {

    @Bean
    public UserBean user() {
        return new UserBean("Привет");
    }
}
