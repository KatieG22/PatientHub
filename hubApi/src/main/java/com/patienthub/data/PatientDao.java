package com.patienthub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.patienthub.model.Doctor;
import com.patienthub.model.Hospital;
import com.patienthub.model.Patient;
import com.patienthub.utils.Database;
import com.patienthub.utils.ProdDbConfig;

public class PatientDao implements Dao<Patient> {
    private static Connection con = Database.getConnection(new ProdDbConfig());

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
    public boolean save(Patient patient) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into hpatient (pps, eirCode, registeredBy, currentDoctor)" + "Values(?,?,?,? )");

            Hospital hospital = patient.getHospital();
            Doctor doc = patient.getCurrentDoctor();

            stmt.setString(1, patient.getPps());
            stmt.setString(2, hospital.getEirCode());
            stmt.setString(3, doc.getPps());
            stmt.setString(4, doc.getPps());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
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

    protected Patient processResultSet(ResultSet rs) {
        Patient patient = new Patient();
        Doctor registrationDoc = new Doctor();
        Doctor currentDoc = new Doctor();

        try {
            if (rs.next() && rs.getRow() != 0) {
                patient.setPatientID(rs.getInt("patientID"));
                patient.setPps(rs.getString("pps"));
                registrationDoc.setPps(rs.getString("registeredBy"));
                patient.setRegisteredBy(registrationDoc);
                currentDoc.setPps(rs.getString("currentDoc"));
                patient.setCurrentDoctor(currentDoc);
                patient.setHospital(new Hospital(rs.getString("eirCode")));
                return patient;
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
