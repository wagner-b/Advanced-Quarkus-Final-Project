package tech.ada.security;

import io.smallrye.jwt.build.Jwt;

public class JwtGenerator {
    public String generateJws() {
        return Jwt.claims().sign();
    }
}
