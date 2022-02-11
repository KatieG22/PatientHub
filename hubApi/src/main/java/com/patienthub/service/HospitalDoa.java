package com.patienthub.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.patienthub.model.Hospital;
import com.patienthub.utils.Database;
import com.patienthub.utils.DbConfig;

public class HospitalDoa {
    private Connection con;

    public HospitalDoa(DbConfig dbConfig) {
        con = Database.getConnection(dbConfig);

    }

    public Hospital create(Hospital detail) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into hospital (hName, contactNum, contactEmail," +
                            "eirCode,addressLine,county,city)" +
                            "Values(?, ?, ?, ?, ?, ?, ? )");
            stmt.setString(1, detail.getName());
            stmt.setString(2, detail.getContactNum());
            stmt.setString(3, detail.getContactEmail());
            stmt.setString(4, detail.getEirCode());
            stmt.setString(5, detail.getAddressLine());
            stmt.setString(6, detail.getCounty());
            stmt.setString(7, detail.getCity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage() + "<---- cause");
            throw new WebApplicationException("Hospital already registered", Status.BAD_REQUEST);
        }
        return detail;
    }

    public List<Hospital> fetchAll() {
        List<Hospital> hospitals = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String query = "select * from hospital;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Hospital hospital = processResultSet(rs);
                hospitals.add(hospital);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return hospitals;
    }

    protected Hospital processResultSet(ResultSet rs) {
        Hospital hospital = new Hospital();
        try {
            hospital.setName(rs.getString("hName"));
            hospital.setContactNum(rs.getString("contactNum"));
            hospital.setContactEmail(rs.getString("contactEmail"));
            hospital.setEirCode(rs.getString("eirCode"));
            hospital.setAddressLine(rs.getString("addressLine"));
            hospital.setCounty(rs.getString("county"));
            hospital.setCity(rs.getString("city"));
            hospital.setIsHq(rs.getBoolean("isHq"));
            hospital.setHasAdmin(rs.getBoolean("hasAdmin"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hospital;

    }

    public void resetTable() {

        try {
            Statement stmt = con.createStatement();
            String query = "truncate hospital;";
            stmt.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
