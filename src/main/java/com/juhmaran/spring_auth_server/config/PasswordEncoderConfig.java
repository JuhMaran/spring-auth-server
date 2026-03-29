package com.juhmaran.spring_auth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * spring-auth-server
 *
 * @author Juliane Maran
 * @since 29/03/2026
 */
@Configuration
public class PasswordEncoderConfig {

  /**
   * Mapa com diferentes enconders para comparação e uso manual
   */
  @Bean
  public Map<String, PasswordEncoder> encoders() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();

    // BCrypt (padrão mais comum)
    encoders.put("bcrypt", new BCryptPasswordEncoder(12));

    // PBKDF2 (compatibilidade alta)
    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("", 16, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256));

    // SCrypt (alto uso de memória)
    encoders.put("scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64));

    // Argon2 (mais moderno e recomendado)
    encoders.put("argon2", new Argon2PasswordEncoder(16, 32, 1, 65536, 3));

    return encoders;
  }

}
