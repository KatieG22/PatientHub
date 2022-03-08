package com.patienthub.api;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.data.HospitalAdminDao;

import com.patienthub.model.Hospital;

import org.junit.Test;

public class HospitalAdminResourceTest extends BaseSetup {

    @Test
    public void testHospitalAdminCanSignUp() {
        Hospital hospital = hospitalAdmin.getHospital();
        hospitalDao.save(hospital);

        String eirCode = hospital.getEirCode();
        WebTarget hospitalTarget = target.path("v1/hospitaladmin/register").queryParam("eirCode", eirCode);
        Response response = hospitalTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(hospitalAdmin, MediaType.APPLICATION_JSON));
        System.out.println(response.readEntity(String.class) +
                "----------------------------");
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());

    }

    @Test
    public void testInvalidEirCodeCannotSignUp() {
        String eirCode = hospital.getEirCode();
        WebTarget hospitalTarget = target.path("v1/hospitaladmin/register").queryParam("eirCode", eirCode);
        Response response = hospitalTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(hospitalAdmin, MediaType.APPLICATION_JSON));
        assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    }

    @Test
    public void testHospitalWithExistingAdminCannotSignUpNewAdmin() {
        // check that a hosptial that already has an admin cannot have multiple
        hospitalAdminDao = new HospitalAdminDao();
        hospitalDao.save(hospital);
        // add saved user to hospital admin table
        userDao.save(hospitalAdmin);
        hospitalAdminDao.save(hospitalAdmin);

        String eirCode = hospital.getEirCode();
        WebTarget hospitalTarget = target.path("v1/hospitaladmin/register").queryParam("eirCode", eirCode);
        Response response = hospitalTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(hospitalAdmin, MediaType.APPLICATION_JSON));

        assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());

    }

}
