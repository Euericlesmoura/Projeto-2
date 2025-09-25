package br.com.senai.logistica.controller;

import br.com.senai.logistica.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public LoginController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest login){

        //Etapa 1. Autenticação

        //Cria um pedido de autenticação
        var authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());

        //Entrega o pedido ao Gerente
        Authentication auth = authenticationManager.authenticate(authToken);


        //Etapa 2. Geração de Token

        //Instant now = registra o momento atual (data, hora, segundo ..)
        Instant now = Instant.now();
        long validade = 3600L; // 1 Hora em segundos.

        //Construindo o Token
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("urbanswift-api") // Quem emitiu o token.
                .issuedAt(now) // Quando foi emitido.
                .expiresAt(now.plusSeconds(validade)) // Quando expira.
                .subject(auth.getName()) // A quem o token pertence (o email do usuário).
                .claim("roles", auth.getAuthorities()) // Informações extras, como os perfis do usuário.
                .build();

        //Define o cabeçalho do Token = como descriptografar
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

        //Gerando a string final = o codigo Token
        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

        //Retorna o Token
        return ResponseEntity.ok(token);

    }
}
