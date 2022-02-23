package com.patienthub.service;

import com.patienthub.data.Dao;
import com.patienthub.data.HospitalDao;
import com.patienthub.model.Hospital;
import com.patienthub.webexceptions.DuplicateData;

public class HospitalService {

    private static Dao<Hospital> dao = new HospitalDao();

    public void save(Hospital hospital) {

        // check if hospital exists
        if (dao.exists(hospital)) {
            throw new DuplicateData("Hospital with Eircode " + hospital.getEirCode() + " exists");
        } else {

            dao.save(hospital);
        }

    }

    public void delete(Hospital hospital) {
        dao.delete(hospital);
    }

}
