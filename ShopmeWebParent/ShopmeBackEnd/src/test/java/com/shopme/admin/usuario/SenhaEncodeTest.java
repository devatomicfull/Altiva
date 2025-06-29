package com.shopme.admin.usuario;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class SenhaEncodeTest {
    @Test
    public void testEncodeSenha() {
        BCryptPasswordEncoder senhaEncoder = new BCryptPasswordEncoder();
        String senhaBruta = "teste";
        String senhaEncodada = senhaEncoder.encode(senhaBruta);

        System.out.println(senhaEncodada);
        boolean matches = senhaEncoder.matches(senhaBruta, senhaEncodada);

        assertThat(matches).isTrue();
    }
}
