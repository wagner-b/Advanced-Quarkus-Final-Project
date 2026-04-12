package tech.ada.security;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import tech.ada.dto.TokenResponseDTO;

@ApplicationScoped
public class JwtGenerator {
    @ConfigProperty(name = "smallrye.jwt.new-token.lifespan")
    long jwtDuration;

    public TokenResponseDTO generateJws() {
        String token = Jwt.claims().sign();
        return new TokenResponseDTO(token, jwtDuration);
    }
}
