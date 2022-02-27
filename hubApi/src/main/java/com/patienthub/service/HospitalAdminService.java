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

        if (hospitalDao.doesEirCodeExist(eirCode) == false) {
            // throw object does not exist error
            throw new ObjectDoesNotExist("hospital with eircode " + eirCode + " does not exist");
        }

        if (userDao.save(admin) == false) {
            throw new DuplicateData("user with email exists, please login ");

        }

        // check if hospital already has admin
        // query hospital admin table for eircode if it's there then hospital already
        // has an admin
        admin.setHospital(new Hospital(eirCode));
        hospitalAdminDao.save(admin);

    }
}
