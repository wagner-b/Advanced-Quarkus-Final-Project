package tech.ada.security;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtGenerator {
    public String generateJws() {
        return Jwt.claims().sign();
    }
}
