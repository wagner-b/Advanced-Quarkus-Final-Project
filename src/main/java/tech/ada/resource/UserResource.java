package tech.ada.resource;

import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import tech.ada.dto.UserNewDTO;
import tech.ada.dto.UserResponseDTO;
import tech.ada.model.User;
import tech.ada.service.UserService;

import java.util.Map;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService service;
    private final JsonWebToken jwt;

    public UserResource(UserService service, JsonWebToken jwt) {
        this.service = service;
        this.jwt = jwt;
    }

    @POST
    public Response addUser(@Valid UserNewDTO dto) {
        User newUser = service.addUser(dto);

        UserResponseDTO payload = new UserResponseDTO(
                newUser.getName(), newUser.getEmail(),
                newUser.getRole()
        );

        return Response.status(201)
                .header("Content-Type", "application/json")
                .entity(payload)
                .build();
    }

    @GET
    @Authenticated
    @Path("/me")
    public Response getCurrentUser() {
        return Response.status(200)
                .header("Content-Type", "application/json")
                .entity(
                    Map.of(
                        "id", jwt.getSubject(),
                        "groups", jwt.getGroups()
                    )
                )
                .build();
    }
}
