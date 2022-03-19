package com.patienthub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.patienthub.model.Doctor;

import com.patienthub.model.Hospital;
import com.patienthub.utils.Database;
import com.patienthub.utils.ProdDbConfig;
import com.patienthub.model.Hospital;

public class DoctorDao implements Dao<Doctor> {
    private static Connection con = Database.getConnection(new ProdDbConfig());

    @Override
    public Optional<Doctor> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Doctor> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(Doctor doc) {
        Hospital currentHospital = doc.getCurrentHospital();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into doctor (pps, currentHospital)" +
                            "Values(?, ?)");
            stmt.setString(1, doc.getPps());
            stmt.setString(2, currentHospital.getEirCode());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return false;

        }

    }

    @Override
    public void update(Doctor t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Doctor t) {
        // TODO Auto-generated method stub

    }

    protected Doctor processResultSet(ResultSet rs) {
        Doctor doctor = new Doctor();
        Hospital currentHospital = new Hospital();
        try {
            doctor.setFirstName(rs.getString("firstName"));
            doctor.setLastName(rs.getString("lastName"));
            doctor.setContactNum(rs.getString("contactNo"));
            doctor.setEmail(rs.getString("email"));
            doctor.setPps(rs.getString("pps"));
            doctor.setGender(rs.getString("gender"));
            doctor.setisactive(rs.getBoolean("isactive"));
            doctor.setPassword(rs.getString("password"));
            doctor.setStaffID(rs.getInt("staffID"));
            doctor.setSpectiality(rs.getString("spectiality"));
            currentHospital.setEirCode(rs.getString("currentHospital"));
            return doctor;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
