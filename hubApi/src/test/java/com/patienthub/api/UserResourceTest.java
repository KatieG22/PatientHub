package com.patienthub.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.model.Credentials;
import com.patienthub.model.WebToken;
import com.patienthub.service.AuthService;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserResourceTest extends BaseSetup {
    private static AuthService authService = new AuthService();

    @Test
    public void testAuthenticatedUserCanGetCridentials() {
        String email = hospitalAdmin.getEmail();
        String password = hospitalAdmin.getPassword();
        userDao.save(hospitalAdmin);
        WebToken token = authService.authenticate(new Credentials(email, password));
        WebTarget userTarget = target.path("v1/user/detail");
        Response response = userTarget.request(MediaType.APPLICATION_JSON_TYPE).header(HttpHeaders.AUTHORIZATION,
                "Bearer " + token.getToken()).get();

        System.out.println(response.readEntity(String.class));

        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }
}
