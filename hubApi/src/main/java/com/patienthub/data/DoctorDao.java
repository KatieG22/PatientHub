package com.patienthub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Optional<Doctor> get(long staffID) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stmt = con.prepareStatement("select * from getDoctor where staffID = ?");
            stmt.setLong(1, staffID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getRow() != 0) {
                Doctor doctor = processResultSet(rs);
                Optional<Doctor> opt = Optional.ofNullable(doctor);
                return opt;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public Optional<Doctor> getByPps(String ppsNumber) {
        Optional<Doctor> opt;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from getDoctor where pps = ?");
            stmt.setString(1, ppsNumber);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Doctor doctor = processResultSet(rs);
            opt = Optional.ofNullable(doctor);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            opt = Optional.ofNullable(null);

        }

        return opt;
    }

    @Override
    public List<Doctor> getAll() {
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from getDoctor");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor doctor = processResultSet(rs);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public boolean save(Doctor doc) {
        Hospital currentHospital = doc.getCurrentHospital();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into doctor (pps, currentHospital, specialization)" +
                            "Values(?, ?, ?)");

            stmt.setString(1, doc.getPps());
            stmt.setString(2, currentHospital.getEirCode());
            stmt.setString(3, doc.getSpecialization());
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
    public void delete(Doctor doc) {

        // TODO Auto-generated method stub

    }

    public ArrayList<Doctor> getAllDoctorsInHospital(Hospital hospital) {
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from getDoctor where currentHospital = ? ");
            stmt.setString(1, hospital.getEirCode());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor doctor = processResultSet(rs);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doctors;
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
            doctor.setStaffID(rs.getLong("staffID"));
            doctor.setSpecialization(rs.getString("specialization"));
            currentHospital.setEirCode(rs.getString("currentHospital"));
            return doctor;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
