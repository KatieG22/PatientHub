package com.patienthub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.patienthub.model.Hospital;
import com.patienthub.utils.Database;

import com.patienthub.utils.ProdDbConfig;

public class HospitalDao implements Dao<Hospital> {
    private static Connection con = Database.getConnection(new ProdDbConfig());

    /** Checking if there is a hospital in the database */
    @Override
    public Optional<Hospital> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**  Accessing hospital by eircode by connecting to the database*/
    public Hospital getByEirCode(String eirCode) {
        // https://coderanch.com/t/646858/databases/implement-DAO-update-method
        try {
            PreparedStatement stmt = con.prepareStatement("select * from hospital where eirCode=?");
            stmt.setString(1, eirCode);
            ResultSet rs = stmt.executeQuery();
            // if item present in db then it'll return at least one row
            if (rs.next() && rs.getRow() != 0) {
                return processResultSet(rs);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return null;

    }
    /**  Listing all the hospitals in the database*/
    @Override
    public List<Hospital> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /** Creating the hospital and storing the information in the database*/ 
    @Override
    public boolean save(Hospital hospital) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into hospital (hName, contactNum, contactEmail," +
                            "eirCode,addressLine,county,city)" +
                            "Values(?, ?, ?, ?, ?, ?, ? )");
            stmt.setString(1, hospital.getName());
            stmt.setString(2, hospital.getContactNum());
            stmt.setString(3, hospital.getContactEmail());
            stmt.setString(4, hospital.getEirCode());
            stmt.setString(5, hospital.getAddressLine());
            stmt.setString(6, hospital.getCounty());
            stmt.setString(7, hospital.getCity());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage() + "<---- cause");
            return false;

        }

    }

    /** Updating hospital information */
    @Override
    public void update(Hospital hospital) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE hospital SET hName=?, contactNum=?, contactEmail=?, eirCode=?, addressLine=?, county=?, city=? WHERE eirCode=?");
            stmt.setString(1, hospital.getName());
            stmt.setString(2, hospital.getContactNum());
            stmt.setString(3, hospital.getContactEmail());
            stmt.setString(4, hospital.getEirCode());
            stmt.setString(5, hospital.getAddressLine());
            stmt.setString(6, hospital.getCounty());
            stmt.setString(7, hospital.getCity());
            stmt.setString(8, hospital.getEirCode());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** Updating the admin status by inputtig eircode of hospital.  */
   
   
    public void updateAdminStatus(Hospital hospital) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE hospital SET hasAdmin=? WHERE eirCode=?");
            stmt.setBoolean(1, hospital.hasAdmin());
            stmt.setString(2, hospital.getEirCode());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Hospital hospital) {
        // TODO Auto-generated method stub

        try {
            PreparedStatement stmt = con.prepareStatement("delete from hospital where eircode= ?");
            stmt.setString(1, hospital.getEirCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("---- deleting");
            e.printStackTrace();
        }

    }
    /** Making a database row into a hospital object */
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
    // public boolean exists(Hospital hospital) {

    // try {
    // PreparedStatement stmt = con.prepareStatement("select * from hospital where
    // eircode= ?");
    // stmt.setString(1, hospital.getEirCode());
    // ResultSet rs = stmt.executeQuery();
    // return rs.next();
    // } catch (SQLException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // return false;
    // }

    // }

}

// public class HospitalDoa implements Dao<Hospital> {
// private Connection con = Database.getConnection(new ProdDbConfig());;

// public Hospital create(Hospital detail) {
// try {
// PreparedStatement stmt = con.prepareStatement(
// "insert into hospital (hName, contactNum, contactEmail," +
// "eirCode,addressLine,county,city)" +
// "Values(?, ?, ?, ?, ?, ?, ? )");
// stmt.setString(1, detail.getName());
// stmt.setString(2, detail.getContactNum());
// stmt.setString(3, detail.getContactEmail());
// stmt.setString(4, detail.getEirCode());
// stmt.setString(5, detail.getAddressLine());
// stmt.setString(6, detail.getCounty());
// stmt.setString(7, detail.getCity());
// stmt.executeUpdate();
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// System.out.println(e.getMessage() + "<---- cause");
// throw new WebApplicationException("Hospital already registered",
// Status.BAD_REQUEST);
// }
// return detail;
// }

// public List<Hospital> fetchAll() {
// List<Hospital> hospitals = new ArrayList<>();
// try {
// Statement stmt = con.createStatement();
// String query = "select * from hospital;";
// ResultSet rs = stmt.executeQuery(query);
// while (rs.next()) {
// Hospital hospital = processResultSet(rs);
// hospitals.add(hospital);
// }
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }

// return hospitals;
// }

// protected Hospital processResultSet(ResultSet rs) {
// Hospital hospital = new Hospital();
// try {
// hospital.setName(rs.getString("hName"));
// hospital.setContactNum(rs.getString("contactNum"));
// hospital.setContactEmail(rs.getString("contactEmail"));
// hospital.setEirCode(rs.getString("eirCode"));
// hospital.setAddressLine(rs.getString("addressLine"));
// hospital.setCounty(rs.getString("county"));
// hospital.setCity(rs.getString("city"));
// hospital.setIsHq(rs.getBoolean("isHq"));
// hospital.setHasAdmin(rs.getBoolean("hasAdmin"));

// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return hospital;

// }

// public void resetTable() {

// try {
// Statement stmt = con.createStatement();
// String query = "truncate hospital;";
// stmt.executeQuery(query);
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }

// }

// }
