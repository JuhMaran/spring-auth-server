package com.juhmaran.spring_auth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Security Configuration
 *
 * @author Juliane Maran
 * @since 29/03/2026
 */
@Configuration
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService() {

    UserDetails user = User.withDefaultPasswordEncoder()
      .username("admin")
      .password("password")
      .roles("ADMIN")
      .build();

    System.out.println(user.getPassword());

    return new InMemoryUserDetailsManager(user);

    // Exemplo de Saída: {bcrypt}$2a$10$9lLwil5X888t0SBlMbBzeu60H.4JKNA5KYMj2vQF070z7vJNi.qOK
  }

}
