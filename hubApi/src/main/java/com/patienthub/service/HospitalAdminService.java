package com.patienthub.service;

import com.patienthub.data.Dao;
import com.patienthub.data.HospitalAdminDao;
import com.patienthub.data.HospitalDao;
import com.patienthub.data.UserDao;
import com.patienthub.model.HospitalAdmin;
import com.patienthub.webexceptions.DuplicateData;
import com.patienthub.webexceptions.ObjectDoesNotExist;

public class HospitalAdminService {
    private static Dao<HospitalAdmin> hospitalAdminDao = new HospitalAdminDao();

    public void save(HospitalAdmin admin, String eirCode) {
        HospitalDao hospitalDao = new HospitalDao();
        UserDao userDao = new UserDao();

        if (hospitalDao.doesEirCode(eirCode) == false) {
            // throw object does not exist error
            throw new ObjectDoesNotExist("hospital with eircode " + eirCode + " does not exist");
        }

        if (userDao.save(admin) == false) {
            throw new DuplicateData("use email exists, please login ");

        }

        admin.assignHospitalEircode(eirCode);
        hospitalAdminDao.save(admin);

    }
}
