package com.patienthub.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
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

    public Hospital generateHospital() {
        Hospital hospital = new Hospital(faker.address().cityName(), faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(), faker.address().zipCode(), faker.address().streetAddress(),
                "dublin", "dublin",
                true, false, true);
        return hospital;
    }

    public Doctor generateDoctor(Hospital hosp) {
        Doctor doctor = new Doctor();
        doctorDao = new DoctorDao();
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

    @Before
    public void setUp() throws Exception {
        userDao = new UserDao();
        currentHospital = new Hospital(faker.address().cityName(),
                faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(), faker.address().zipCode(),
                "church street", "ireland", "dublin", true, false, true);
        // currentHospital = generateHospital();
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

    @After
    public void tearDown() throws Exception {
        List<Doctor> doctors = doctorDao.getAll();
        for (Doctor doctor : doctors) {
            userDao.delete(doctor);
        }
        hospitalDao.delete(currentHospital);
    }

    @Test
    public void testSaveMethodCreatesDoctorInDB() {
        doctorDao = new DoctorDao();
        doctorDao.save(doc);
        Optional<Doctor> opt = doctorDao.getByPps(doc.getPps());
        assertTrue(opt.isPresent());

    }

    @Test
    public void testAllDoctorsForCurrentHospitalIsFetched() {
        int doctorCount = 0;
        while (doctorCount != 3) {
            Doctor doctor = generateDoctor(currentHospital);
            System.out.println("Eir code stuff");
            System.out.println(doctor.getCurrentHospital().getEirCode());
            userDao.save(doctor);
            doctorDao.save(doctor);
            doctorCount++;
        }
        doctorDao = new DoctorDao();
        ArrayList<Doctor> doctors = doctorDao.getAllDoctorsInHospital(currentHospital);
        int numberOFDoctors = doctors.size();
        System.out.println("how many docs ------>" + numberOFDoctors);
        assertEquals(3, numberOFDoctors);

    }

}
