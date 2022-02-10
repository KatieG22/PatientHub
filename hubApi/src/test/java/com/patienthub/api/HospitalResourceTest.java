package com.patienthub.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.patienthub.model.Hospital;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HospitalResourceTest {

    private HttpServer server;
    private WebTarget target;

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
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testcreateHospital() {
        WebTarget hospitalWebTarget = target.path("v1/hospital");
        // Hospital h = new Hospital("dublin city", "+2348169084566",
        // "tumise@gmail.com", "E19ttt", "21 adesola",
        // "nigeria", "lago",
        // true, false, true);
        Hospital h = new Hospital();
        Response response = hospitalWebTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(h, MediaType.APPLICATION_JSON));
        System.out.println(response.toString());
        assertEquals(400, response.getStatus());
    }
}
