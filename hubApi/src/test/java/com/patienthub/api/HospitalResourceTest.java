package com.patienthub.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.config.TestAppConfig;
import com.patienthub.model.Hospital;
import com.patienthub.service.HospitalService;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HospitalResourceTest extends JerseyTest {

    private HttpServer server;
    private WebTarget target;
    private HospitalService service;

    @Override
    protected Application configure() {
        return new TestAppConfig();
    }

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new
        // org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
        service = new HospitalService();
    }

    @After
    public void tearDown() throws Exception {

        server.shutdownNow();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testUserCanCreateHospital() {
        WebTarget hospitalWebTarget = target.path("v1/hospital");
        Hospital h = new Hospital("dublin city", "+2348169084566",
                "tumise@gmail.com", "E19ttt", "21 adesola",
                "nigeria", "lago",
                true, false, true);
        Response response = hospitalWebTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(h, MediaType.APPLICATION_JSON));

        service.delete(h);
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUserMustFill_RequiredFieldsToCreateHospital() {
        WebTarget hospitalWebTarget = target.path("v1/hospital");
        Hospital h = new Hospital();
        Response response = hospitalWebTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(h, MediaType.APPLICATION_JSON));
        System.out.println(response.toString());
        assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
