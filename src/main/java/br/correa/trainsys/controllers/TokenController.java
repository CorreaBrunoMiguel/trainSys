package br.correa.trainsys.controllers;

import br.correa.trainsys.controllers.dto.LoginRequest;
import br.correa.trainsys.controllers.dto.LoginResponse;
import br.correa.trainsys.repositories.UserRepository;
import br.correa.trainsys.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("auth/login")
public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final TokenService token;
    private final UserRepository repo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public TokenController(JwtEncoder jwtEncoder, TokenService token, UserRepository repo) {
        this.jwtEncoder = jwtEncoder;
        this.token = token;
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
            ){

        if (token.loginCorrect(loginRequest)) {
            var user = repo.findByEmail(loginRequest.email());

            var now = Instant.now();
            var expireIn = 86400L;
            var claims = JwtClaimsSet
                    .builder()
                    .issuer("trainSys")
                    .subject(user.get().getId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expireIn))
                    .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok(new LoginResponse(
                    jwtValue,
                    expireIn,
                    "Usuário Logado com Sucesso"
            ));
        }

        return null;
    }


}
