package tech.ada.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.ada.dto.TokenResponseDTO;
import tech.ada.dto.UserRequestDTO;
import tech.ada.security.JwtGenerator;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    JwtGenerator jwtGenerator;
    public AuthResource(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @Path("/token")
    @POST
    public Response generateJws(@Valid UserRequestDTO RequestDTO) {
        TokenResponseDTO dto = jwtGenerator.generateJws(RequestDTO);
        return Response.status(201)
                .header("Content-Type", "application/json")
                .entity(dto)
                .build();
    }
}
