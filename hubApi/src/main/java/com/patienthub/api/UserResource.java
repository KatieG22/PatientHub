
package com.patienthub.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.model.Credentials;
import com.patienthub.service.UserService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("v1/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService userService= new UserService();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    public Response authenticate(@Valid Credentials credential) {
        String token = userService.authenticate(credential);
        return Response.status(Status.OK.getStatusCode()).entity(token).build();
    }
}
