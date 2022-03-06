package com.patienthub.data;

import java.util.List;
import java.util.Optional;

import com.patienthub.model.Patient;

public class PatientDao implements Dao<Patient> {

    /** Checking to see if Patient is in the list  */
    @Override
    public Optional<Patient> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Patient> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(Patient t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update(Patient t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Patient t) {
        // TODO Auto-generated method stub

    }

}
