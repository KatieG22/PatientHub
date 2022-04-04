package com.patienthub.api;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.patienthub.annotation.Secured;
import com.patienthub.data.HospitalAdminDao;
import com.patienthub.model.Doctor;
import com.patienthub.model.HospitalAdmin;
import com.patienthub.model.User;
import com.patienthub.service.HospitalAdminService;
import com.patienthub.webexceptions.InvalidInput;
import com.patienthub.webexceptions.UserDoesNotExist;

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

    /**
     * 
     * @param securityContext
     * @return List of doctors
     *         The RolesAllowed annotation allows developer
     *         restrict endpoint to only passed in roles
     * 
     */
    @GET
    @Secured
    @Path("/doctorsList")
    @RolesAllowed("HospitalAdmin")
    public Response doctorsList(@Context SecurityContext securityContext) {
        Principal requestUser = securityContext.getUserPrincipal();
        HospitalAdminDao hospitalAdminDao = new HospitalAdminDao();
        User user = (User) requestUser;
        Optional<HospitalAdmin> Optionaladmin = hospitalAdminDao.getAdminByPPS(user.getPps());
        if (Optionaladmin.isPresent()) {
            HospitalAdmin admin = Optionaladmin.get();
            List<Doctor> doctors = hospialAdminservice.fetchAllDoctors(admin);
            return Response.status(Status.OK.getStatusCode()).entity(doctors).build();
        } else {
            System.out.print("admin not present");
            throw new UserDoesNotExist("admin user does not exist");
        }

    }

    @POST
    @Secured
    @Path("/createDoctor")
    public Response createDoctor(@Context SecurityContext securityContext, Doctor doctor) {
        // principal represents loged in user.
        Principal user = securityContext.getUserPrincipal();
        HospitalAdminDao hospitalAdminDao = new HospitalAdminDao();
        // fetch admin user
        User u = (User) user;
        Optional<HospitalAdmin> Optionaladmin = hospitalAdminDao.getAdminByPPS(u.getPps());
        // fetch username--> getUserFrom DB --> check role --> call service
        if (Optionaladmin.isPresent()) {
            HospitalAdmin admin = Optionaladmin.get();
            Doctor createdDoctor = hospialAdminservice.creaateDoctor(admin, doctor);
            return Response.status(Status.CREATED.getStatusCode()).entity(createdDoctor).build();
        } else {
            // return error resposne
            throw new UserDoesNotExist("admin user does not exist");
        }

    }

}