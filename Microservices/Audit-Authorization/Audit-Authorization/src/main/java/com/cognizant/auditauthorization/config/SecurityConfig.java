package com.cognizant.auditauthorization.config;



import com.cognizant.auditauthorization.service.ProjectManagerService;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ProjectManagerService projectManagerService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(projectManagerService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
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
		}).and().csrf()
              .disable()
              .authorizeRequests()
              .antMatchers("/auth/**","/login")//only accepts these url's without token
              .permitAll()
              .antMatchers("/actuator/**","/v2/api-docs","/swagger-resources/**","/swagger-ui/**").permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);//adding filter for every request.
	  httpSecurity.headers().frameOptions().disable();

  }
}
