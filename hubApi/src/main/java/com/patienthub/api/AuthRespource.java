package com.patienthub.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.model.Credentials;
import com.patienthub.model.WebToken;
import com.patienthub.service.AuthService;

@Path("v1/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthRespource {

    private static AuthService authService = new AuthService();

    @POST
    @Path("/signin")
    public Response authenticate(@Valid Credentials credentials) {
        WebToken token = authService.authenticate(credentials);
        System.out.println(token + "token");
        return Response.status(Status.OK.getStatusCode()).entity(token).build();

    }

}
