package com.patienthub.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.config.TestAppConfig;
import com.patienthub.data.HospitalAdminDao;
import com.patienthub.data.HospitalDao;
import com.patienthub.data.UserDao;
import com.patienthub.model.Hospital;
import com.patienthub.model.HospitalAdmin;
import com.patienthub.service.HospitalService;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HospitalAdminResourceTest extends JerseyTest {

    private HttpServer server;
    private WebTarget target;
    private HospitalAdmin hospitalAdmin;
    private Hospital hospital;
    private HospitalService hospitalService;
    private HospitalDao hospitalDao;
    private UserDao userDao;
    private HospitalAdminDao hospitalAdminDao;

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

        target = c.target(Main.BASE_URI);

        hospitalService = new HospitalService();
        hospitalDao = new HospitalDao();
        userDao = new UserDao();

        hospital = new Hospital("dublin city", "+2348169084566",
                "tumise@gmail.com", "E19000", "21 adesola",
                "nigeria", "lago",
                true, false, true);

        hospitalAdmin = new HospitalAdmin("tumise", "alade", "+353898999", "aalde@gmail.com", "000000295",
                "male",
                hospital);
        hospitalAdmin.setPassword("password");
    }

    @After
    public void tearDown() throws Exception {
        hospitalService.delete(hospital);
        userDao.delete(hospitalAdmin);
        server.shutdownNow();
    }

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

    @Test
    public void testWhenAdminSignsUpSuccessFully_HospitalHasAdminIsSetToTrue() {
        // save hospital in db because only saved hopitals can have admins
        Hospital hospital = hospitalAdmin.getHospital();
        hospitalDao.save(hospital);

        String eirCode = hospital.getEirCode();
        WebTarget hospitalTarget = target.path("v1/hospitaladmin/register").queryParam("eirCode", eirCode);
        hospitalTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(hospitalAdmin, MediaType.APPLICATION_JSON));
        Hospital dbHospital = hospitalDao.getByEirCode(eirCode);
        assertTrue(dbHospital.hasAdmin());

    }

}
