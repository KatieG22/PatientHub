package com.patienthub.data;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.github.javafaker.Faker;
import com.patienthub.model.Doctor;
import com.patienthub.model.Gender;
import com.patienthub.model.Hospital;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoctorDaoTest {
    private static Faker faker = new Faker();
    private static Doctor doc;

    private static UserDao userDao;
    private static DoctorDao doctorDao;
    private static HospitalDao hospitalDao;
    private static Hospital currentHospital;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDao();
        currentHospital = new Hospital(faker.address().cityName(), faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(), faker.address().zipCode(), faker.address().streetAddress(),
                faker.address().country(), faker.address().state(),
                true, false, true);

        hospitalDao = new HospitalDao();
        hospitalDao.save(currentHospital);

        doc = new Doctor();
        String userRole = doc.getClass().getSimpleName();
        doc.setFirstName(faker.name().firstName());
        doc.setLastName(faker.name().lastName());
        doc.setContactNum(faker.phoneNumber().cellPhone());
        doc.setEmail(faker.internet().emailAddress());
        doc.setPps(faker.bothify("??#?##??##"));
        doc.setGender(Gender.MALE.name());
        doc.setRole(userRole);
        doc.setPassword(faker.bothify("??#??#"));
        doc.setSpecialization("surgery");
        doc.setCurrentHospital(currentHospital);
        userDao.save(doc);

    }

    @Test
    public void testSaveMethodCreatesDoctorInDB() {
        doctorDao = new DoctorDao();
        doctorDao.save(doc);
        Optional<Doctor> opt = doctorDao.getByPps(doc.getPps());
        assertTrue(opt.isPresent());

    }

    @After
    public void tearDown() throws Exception {
        userDao.delete(doc);
        hospitalDao.delete(currentHospital);
    }

}
