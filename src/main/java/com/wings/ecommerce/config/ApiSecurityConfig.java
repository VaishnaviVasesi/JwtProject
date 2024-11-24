package com.wings.ecommerce.config;

import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) throws Exception{
            }
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests()
                .antMatchers("/api/auth/consumer/*").hasAuthority("CONSUMER")
                .antMatchers("/api/auth/seller/**").hasAuthority("SELLER")
                .antMatchers("/api/public/*").permitAll()
                .and().exceptionHandling().authenticationEntryPoint(new ApiAuthenticationEntryPoint())
                .and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

    }
    @Bean
    public RegistrationBean jwtAuthFilerRegister(JwtAuthenticationFilter filter){
        return null;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return null;

    }
}
