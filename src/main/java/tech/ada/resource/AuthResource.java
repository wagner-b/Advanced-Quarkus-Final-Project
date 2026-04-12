package tech.ada.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.ada.security.JwtGenerator;
import java.util.Map;

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
    public Response generateJws() {
        String token = jwtGenerator.generateJws();
        return Response.status(201)
                .header("Content-Type", "application/json")
                .entity(Map.of("token", token))
                .build();
    }
}
