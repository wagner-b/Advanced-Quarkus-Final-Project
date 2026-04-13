package tech.ada.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.ada.dto.UserNewDTO;
import tech.ada.dto.UserResponseDTO;
import tech.ada.model.User;
import tech.ada.service.UserService;

import java.net.URI;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @POST
    public Response addUser(@Valid UserNewDTO dto) {
        User newUser = service.addUser(dto);

        UserResponseDTO payload = new UserResponseDTO(
                newUser.getUsername(), newUser.getEmail(),
                newUser.getRole()
        );

        return Response.status(201)
                .header("Content-Type", "application/json")
                .entity(payload)
                .build();
    }
}
