package com.shopme.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    /**
     * Cria e registra um bean do tipo {@link PasswordEncoder} utilizando o algoritmo de hash BCrypt.
     *
     * <p>
     * Este encoder é utilizado para proteger senhas armazenadas no banco de dados, aplicando
     * um processo de criptografia unidirecional com salt aleatório e múltiplas iterações,
     * aumentando a segurança contra ataques de força bruta.
     * </p>
     *
     * @return uma instância de {@link BCryptPasswordEncoder}, implementando {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Configura a cadeia de filtros de segurança da aplicação com as regras de autorização definidas.
     *
     * <p>
     * Neste exemplo, todas as requisições são permitidas sem autenticação, o que é comum em
     * ambientes de desenvolvimento ou para sistemas públicos que não exigem controle de acesso.
     * </p>
     *
     * @param http objeto {@link HttpSecurity} fornecido pelo Spring Security para definir as regras
     *             de autenticação, autorização, login, logout, proteção CSRF, entre outras.
     * @return uma instância de {@link SecurityFilterChain} contendo as configurações de segurança aplicadas
     * @throws Exception caso ocorra algum erro durante a construção da cadeia de filtros
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Permite livre acesso a qualquer URL da aplicação, sem autenticação
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().permitAll());

        // Constrói e retorna a cadeia de filtros de segurança configurada
        return http.build();
    }
}

