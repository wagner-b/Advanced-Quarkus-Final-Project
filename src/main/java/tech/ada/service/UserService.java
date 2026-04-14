package tech.ada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import tech.ada.dto.UserNewDTO;
import tech.ada.model.User;

@ApplicationScoped
public class UserService {

    @Transactional
    public User addUser(UserNewDTO dto) {
        // returns 409 if email is already registered
        boolean exists = User.find("email", dto.email())
                .firstResultOptional().isPresent();
        if (exists) {
            throw new WebApplicationException(
                    "This email address is " +
                            "already registered.", 409
            );
        }
        User user = new User(
                dto.name(),
                dto.email(),
                dto.password(),
                "USER"
        );
        user.persist();
        return user;
    }
}
