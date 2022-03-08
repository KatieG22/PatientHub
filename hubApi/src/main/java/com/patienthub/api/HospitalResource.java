package com.patienthub.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.model.Hospital;
import com.patienthub.service.HospitalService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("v1/hospital")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HospitalResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    private static HospitalService service = new HospitalService();

    @POST
    public Response create(@Valid Hospital hospital) {
        service.save(hospital);
        return Response.status(Status.CREATED.getStatusCode()).entity(hospital).build();
    }
}