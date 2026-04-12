package tech.ada.security;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import tech.ada.dto.TokenResponseDTO;

@ApplicationScoped
public class JwtGenerator {
    long jwtDuration = 3600; // 1 hour

    public TokenResponseDTO generateJws() {
        String token = Jwt.claims().expiresIn(jwtDuration).sign();
        return new TokenResponseDTO(token, jwtDuration);
    }
}
