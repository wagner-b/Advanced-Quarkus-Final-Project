package tech.ada.security;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotAuthorizedException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import tech.ada.dto.TokenResponseDTO;
import tech.ada.dto.UserSignInDTO;
import tech.ada.model.User;

@ApplicationScoped
public class JwtGenerator {
    @ConfigProperty(name = "smallrye.jwt.new-token.lifespan")
    long jwtDuration;

    public TokenResponseDTO generateJws(UserSignInDTO dto) {

        User user = User.<User>find("email", dto.email())
                .firstResultOptional()
                .orElseThrow(() ->
                       new EntityNotFoundException("User not found!")
                );

        boolean validPassword = BcryptUtil.matches(
                dto.password(), user.getPassword()
        );
        if (!validPassword) {
            throw new NotAuthorizedException("Invalid Password!");
        }

        String token = Jwt.claims()
                .claim("id", user.id)
                .claim("role", user.getRole())
                .sign();
        return new TokenResponseDTO(token, jwtDuration);
    }
}
