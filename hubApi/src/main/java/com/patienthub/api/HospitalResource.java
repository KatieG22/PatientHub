package com.patienthub.api;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.patienthub.model.Hospital;
import com.patienthub.service.HospitalDoa;
import com.patienthub.utils.DbConfig;

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

    private final DbConfig dbConfig;
    private HospitalDoa dao;

    @Inject
    public HospitalResource(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
        dao = new HospitalDoa(dbConfig);

    }

    @POST
    public Response create(@Valid Hospital detalis) {
        Hospital hospital = dao.create(detalis);
        return Response.status(200).entity(hospital).build();
    }
}