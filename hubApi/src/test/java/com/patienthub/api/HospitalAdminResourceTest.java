package com.patienthub.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.data.DoctorDao;
import com.patienthub.data.HospitalAdminDao;
import com.patienthub.model.Credentials;
import com.patienthub.model.Doctor;
import com.patienthub.model.Hospital;
import com.patienthub.model.WebToken;
import com.patienthub.service.AuthService;

import org.junit.Test;

public class HospitalAdminResourceTest extends BaseSetup {

    @Test
    public void testHospitalAdminCanSignUp() {
        Hospital hospital = hospitalAdmin.getHospital();
        hospitalDao.save(hospital);
        System.out.print(hospitalAdmin.getRole());
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
    public void testOnlyAnAdminCanAccesssAListOfDoctors() {
        AuthService authService = new AuthService();
        Doctor doctor = generateDoctor(hospital);
        String email = doctor.getEmail();
        String password = doctor.getPassword();

        userDao.save(doctor);
        WebToken token = authService.authenticate(new Credentials(email, password));

        WebTarget hospitalAdminTarget = target.path("v1/hospitaladmin/doctorsList");
        Response response = hospitalAdminTarget.request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,
                        "Bearer " + token.getToken())
                .get();
        assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    /**
     * Test Ensures that calling doctors list endpoint
     * returns a list of doctors belonging to admins hospital
     */
    @Test
    public void testAdminGetsListOfDoctors() {
        DoctorDao doctorDao = new DoctorDao();
        for (int i = 0; i < 6; i++) {
            Doctor doc = generateDoctor(hospital);
            userDao.save(doc);
            doctorDao.save(doc);
        }
        AuthService authService = new AuthService();
        hospitalDao.save(hospital);
        hospitalAdmin.setPps("00000");
        userDao.save(hospitalAdmin);
        HospitalAdminDao hAdminDao = new HospitalAdminDao();
        hAdminDao.save(hospitalAdmin);
        String email = hospitalAdmin.getEmail();
        String password = hospitalAdmin.getPassword();
        WebToken token = authService.authenticate(new Credentials(email, password));
        WebTarget hospitalAdminTarget = target.path("v1/hospitaladmin/doctorsList");
        Response response = hospitalAdminTarget.request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,
                        "Bearer " + token.getToken())
                .get();

        assertEquals(Status.OK.getStatusCode(), response.getStatus());

    }

}
