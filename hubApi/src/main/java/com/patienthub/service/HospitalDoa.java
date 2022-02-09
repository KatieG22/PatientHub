package com.patienthub.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        }
        return detail;
    }

}
