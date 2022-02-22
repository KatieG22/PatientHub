package com.patienthub.service;

import com.patienthub.data.Dao;
import com.patienthub.data.HospitalDao;
import com.patienthub.model.Hospital;

public class HospitalService {

    private static Dao<Hospital> dao = new HospitalDao();

    public void save(Hospital hospital) {

        // check if hospital exists
        if (dao.exists(hospital)) {
            // raise exception
        } else {

            dao.save(hospital);
        }

    }

    public void delete(Hospital hospital) {
        dao.delete(hospital);
    }

}
