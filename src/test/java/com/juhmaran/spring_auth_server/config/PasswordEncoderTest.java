package com.juhmaran.spring_auth_server.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

  Map<String, PasswordEncoder> encoders;

  @BeforeEach
  void setUp() {
    PasswordEncoderConfig config = new PasswordEncoderConfig();
    this.encoders = config.encoders();
  }

  @Test
  @DisplayName("Deve codificar e validar corretamente a senha")
  void shouldEncodeAndMatchPasswords() {
    String rawPassword = "password";

    encoders.forEach((name, encoder) -> {
      try {
        String hash = encoder.encode(rawPassword);
        boolean matches = encoder.matches(rawPassword, hash);

        System.out.println("[" + name + "] matches: " + matches);

        assertTrue(matches, "Falha no encoder: " + name);
      } catch (Exception e) {
        fail("Erro ao testar encoder '" + name + "': " + e.getMessage());
      }
    });
  }

  @Test
  @DisplayName("Não deve validar senha incorreta")
  void shouldFailWithWrongPassword() {
    String rawPassword = "password";
    String wrongPassword = "wrong";

    encoders.forEach((name, encoder) -> {
      try {
        String hash = encoder.encode(rawPassword);
        boolean matches = encoder.matches(wrongPassword, hash);

        System.out.println("[" + name + "] wrong password matches: " + matches);

        assertFalse(matches, "Encoder aceitou senha incorreta: " + name);
      } catch (Exception e) {
        fail("Erro ao testar encoder '" + name + "': " + e.getMessage());
      }
    });
  }


}