package com.patienthub.api;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.model.Credentials;

import org.junit.Test;

public class AuthResouceTest extends BaseSetup {

    @Test
    public void testUserWithValidCridentialsCanLogin() {
        userDao.save(hospitalAdmin);
        WebTarget authTarget = target.path("v1/auth/signin");
        String email = hospitalAdmin.getEmail();
        String password = hospitalAdmin.getPassword();
        Response response = authTarget.request(
                MediaType.APPLICATION_JSON)
                .post(Entity.entity(new Credentials(email, password),
                        MediaType.APPLICATION_JSON));

        // System.out.println(response.readEntity(String.class) +
        // "----------------------------");

        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

}
