package com.ardecs.config;

import com.ardecs.jwtToken.JwtTokenProvider;
import com.ardecs.myAccessDeniedHandler.MyAccessDeniedHandler;
import com.ardecs.myEntryPoint.CommenceEntryPoint;
import com.ardecs.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import com.ardecs.myEntryPoint.CommenceEntryPoint;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 02.07.2019
 * Аннотация @EnableWebSecurity в связке с WebSecurityConfigurerAdapter классом
 * работает над обеспечением аутентификации
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/images/**", "/css/**", "/swagger-ui.html", "/v2/api-docs/**", "/swagger.json", "/swagger-resources/**", "/webjars/**", "/restLogin/**").permitAll()

                .antMatchers(HttpMethod.GET, "/restModel/**", "/brand/**", "/models/**", "/model/**", "/complect/**", "/modComp/**", "/registration")
                .hasAnyRole("VIEWER", "CREATOR", "UPDATER", "ADMIN")

                .antMatchers(HttpMethod.PUT, "/restModel/**", "/brand/**", "/models/**", "/model/**", "/complect/**", "/modComp/**", "/registration")
                .hasAnyRole("UPDATER", "ADMIN")

                .antMatchers(HttpMethod.POST, "/restModel/**", "/brand/**", "/models/**", "/model/**", "/complect/**", "/modComp/**", "/registration")
                .hasAnyRole("CREATOR", "ADMIN")

                .antMatchers(HttpMethod.DELETE, "/restModel/**", "/brand/**", "/models/**", "/model/**", "/complect/**", "/modComp/**", "/registration")
                .hasRole("ADMIN")

                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/brand/list")
                .permitAll();

        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler())
                .and().apply(new JwtConfigurer(jwtTokenProvider))
                .and().exceptionHandling().authenticationEntryPoint(new CommenceEntryPoint());
    }
}
