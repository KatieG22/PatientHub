package com.patienthub.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.patienthub.model.Hospital;

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
    @POST
    public Response createHospital() {
        Hospital hospial = new Hospital("dublin city", "+2348169084566", "tumise@gmail.com", "E19ttt", "21 adesola",
                "nigeria", "lago", true, false, true);
        String details = String.format("\'{name:%s}\'", hospial.getName());
        return Response.status(200).entity(hospial).build();
    }
}