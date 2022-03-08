package com.patienthub.service;

import com.patienthub.data.Dao;
import com.patienthub.data.HospitalDao;
import com.patienthub.model.Hospital;
import com.patienthub.webexceptions.DuplicateData;

public class HospitalService {

    private static Dao<Hospital> dao = new HospitalDao();

    public void save(Hospital hospital) {

        // if hospital isn't saved in db, then it's a dubplicate
        boolean saved = dao.save(hospital);
        if (!saved) {
            throw new DuplicateData("Hospital with Eircode " + hospital.getEirCode() + " exists");
        }

    }

    public void delete(Hospital hospital) {
        dao.delete(hospital);
    }

}
