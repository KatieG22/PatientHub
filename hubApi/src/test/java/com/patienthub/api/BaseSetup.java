package com.patienthub.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import com.github.javafaker.Faker;
import com.patienthub.config.TestAppConfig;
import com.patienthub.data.HospitalAdminDao;
import com.patienthub.data.HospitalDao;
import com.patienthub.data.UserDao;
import com.patienthub.model.Doctor;
import com.patienthub.model.Gender;
import com.patienthub.model.Hospital;
import com.patienthub.model.HospitalAdmin;
import com.patienthub.service.HospitalService;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;

public class BaseSetup extends JerseyTest {

    @Override
    protected Application configure() {
        return new TestAppConfig();
    }

    protected HttpServer server;
    protected WebTarget target;
    protected HospitalAdmin hospitalAdmin;
    protected Hospital hospital;
    protected HospitalService hospitalService;
    protected HospitalDao hospitalDao;
    protected UserDao userDao;
    protected HospitalAdminDao hospitalAdminDao;

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
                Gender.MALE, hospital);
        hospitalAdmin.setPassword("password");
    }

    @After
    public void tearDown() throws Exception {
        hospitalService.delete(hospital);
        userDao.delete(hospitalAdmin);
        server.shutdownNow();
    }

    public Doctor generateDoctor(Hospital hosp) {
        Faker faker = new Faker();
        Doctor doctor = new Doctor();
        String userRole = doctor.getClass().getSimpleName();
        doctor.setFirstName(faker.name().firstName());
        doctor.setLastName(faker.name().lastName());
        doctor.setContactNum(faker.phoneNumber().cellPhone());
        doctor.setEmail(faker.internet().emailAddress());
        doctor.setPps(faker.bothify("??#?##??##"));
        doctor.setGender(Gender.MALE.name());
        doctor.setRole(userRole);
        doctor.setPassword(faker.bothify("??#??#"));
        doctor.setSpecialization("surgery");
        doctor.setCurrentHospital(hosp);

        return doctor;

    }

}
