package com.patienthub.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.patienthub.model.HospitalAdmin;

import com.patienthub.service.HospitalAdminService;
import com.patienthub.webexceptions.InvalidInput;

import javax.ws.rs.core.Response.Status;

@Path("v1/hospitaladmin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HospitalAdminResource {

    public static HospitalAdminService hospialAdminservice = new HospitalAdminService();

    @POST
    @Path("/register")
    public Response create(@QueryParam("eirCode") String eirCode, @Valid HospitalAdmin hospitalAdmin) {
        // TODO: validate phone number, email and pps number
        if (eirCode == null) {
            throw new InvalidInput("please provide an eirCode in url param");
        }

        hospialAdminservice.save(hospitalAdmin, eirCode);
        return Response.status(Status.CREATED.getStatusCode()).entity(hospitalAdmin).build();
    }

}
