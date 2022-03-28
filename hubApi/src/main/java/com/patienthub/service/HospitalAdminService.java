package com.patienthub.service;

import java.util.List;

import com.patienthub.data.Dao;
import com.patienthub.data.DoctorDao;
import com.patienthub.data.HospitalAdminDao;
import com.patienthub.data.HospitalDao;
import com.patienthub.data.UserDao;
import com.patienthub.model.Doctor;
import com.patienthub.model.Hospital;
import com.patienthub.model.HospitalAdmin;
import com.patienthub.webexceptions.DuplicateData;
import com.patienthub.webexceptions.ObjectDoesNotExist;

/**
 * @author Tumise
 * @author Glen
 * @implNote this class handles business logic before performing crud operations
 *           using the hospital admin DAO
 */
public class HospitalAdminService {
    private static Dao<HospitalAdmin> hospitalAdminDao = new HospitalAdminDao();

    public void save(HospitalAdmin admin, String eirCode) {
        HospitalDao hospitalDao = new HospitalDao();
        UserDao userDao = new UserDao();
        Hospital dbHospital = hospitalDao.getByEirCode(eirCode);

        if (dbHospital == null) {
            // throw object does not exist error
            throw new ObjectDoesNotExist("hospital with eircode " + eirCode + " does not exist");
        }
        // check if hospital already has admin
        if (dbHospital != null && dbHospital.hasAdmin() == true) {
            // hospital already has an admin
            throw new DuplicateData("hospital already has an admin");
        }

        if (userDao.save(admin) == false) {
            throw new DuplicateData("user with email exists, please login ");

        }

        Hospital hospital = new Hospital(eirCode);
        admin.setHospital(hospital);
        hospitalAdminDao.save(admin);
        hospital.setHasAdmin(true);
        // update admin status to true
        hospitalDao.updateAdminStatus(hospital);

    }

    public List<Doctor> fetchAllDoctors(HospitalAdmin admin) {
        System.out.print("fetching all doctors");
        Hospital hospital = admin.getHospital();
        DoctorDao doctorDao = new DoctorDao();
        return doctorDao.getAllDoctorsInHospital(hospital);

    }

    public Doctor creaateDoctor(HospitalAdmin admin, Doctor doctor) {
        // when an admin creates a doctor, the doctors hospital is set to
        // the admins hospital. this is because an admin can only create
        // doctors for his or her hospital
        Hospital hospital = admin.getHospital();
        UserDao userDao = new UserDao();
        DoctorDao doctorDao = new DoctorDao();
        String generatedPassword = admin.generatePasswordFor(doctor);
        doctor.setPassword(generatedPassword);
        doctor.setCurrentHospital(hospital);
        userDao.save(doctor);
        doctorDao.save(doctor);
        return doctor;

    }
}
