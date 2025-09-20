package br.com.senai.logistica.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private String secret = "senha-secreta-ultra-mega-power-forte-2157@oi-mae";

// BEANS: Os "Motores" da Nossa Segurança

//1. O GERADOR DE TOKENS (Encoder)
@Bean
public JwtEncoder jwtEncoder() {
    var secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA25HA256");
    return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
}

//2. O VERIFICADOR DE TOKENS (Decoder)
@Bean
public JwtDecoder jwtDecoder() {
    var secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
    return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
}

// 3. O CRIPTOGRAFADOR DE SENHAS
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

// 4. O GERENTE DE AUTENTICAÇÃO
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}

//O FILTRO DE SEGURANÇA
@Bean
public SecurityWebFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {

    http

    .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll();
    return http.build();
    }
}

