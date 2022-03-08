package com.patienthub.service;

import com.patienthub.data.Dao;
import com.patienthub.data.HospitalAdminDao;
import com.patienthub.data.HospitalDao;
import com.patienthub.data.UserDao;
import com.patienthub.model.Hospital;
import com.patienthub.model.HospitalAdmin;
import com.patienthub.webexceptions.DuplicateData;
import com.patienthub.webexceptions.ObjectDoesNotExist;

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
}
