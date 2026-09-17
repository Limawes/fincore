package br.com.fincore.security.service;

import br.com.fincore.security.domain.model.UserSite;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("jwt.secret.key")
    private String SECRET;

    public String tokenGenerate(String userEmail){

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer("api-fincore")
                    .withSubject(userEmail)
                    .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                    .sign(algorithm);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token!");
        }
    }

    public String tokenValidate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            return JWT.require(algorithm)
                    .withIssuer("api-fincore")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (Exception e) {
            return null;
        }
    }

}
