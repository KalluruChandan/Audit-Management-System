package com.cognizant.auditseverity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

/*
 * @author  somesh
 * This layer defines security configurations to access the endpoints.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile(value = {"!test"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://auditmanagement-bucket.s3-website.eu-west-3.amazonaws.com/"
                    ));
                    cors.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    return cors;
                }).and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/severity/db/**","/actuator/**","/v2/api-docs","/swagger-resources/**","/swagger-ui/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(new JwtFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                httpSecurity.headers().frameOptions().disable();
    }


}
