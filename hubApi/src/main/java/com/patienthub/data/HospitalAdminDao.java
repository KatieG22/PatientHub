package com.patienthub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.patienthub.model.Hospital;
import com.patienthub.model.HospitalAdmin;
import com.patienthub.utils.Database;
import com.patienthub.utils.ProdDbConfig;

public class HospitalAdminDao implements Dao<HospitalAdmin> {

    /**  Establishing a connection to the database */
    private static Connection con = Database.getConnection(new ProdDbConfig());

    /** Checking to see if there is admin in the Hospital Admin database */
    @Override
    public Optional<HospitalAdmin> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HospitalAdmin> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /** Creating Hospital admin staff by getting hospital eircode
    and the admins PPSN. In order to get the eircode we must
    get the hospital first
    */ 
    @Override
    public boolean save(HospitalAdmin admin) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into hAdmin (pps, hospital)" + "Values(?, ? )");
            Hospital hospital = admin.getHospital();
            stmt.setString(1, admin.getPps());
            stmt.setString(2, hospital.getEirCode());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage() + "<---- caushhhhhe");

        }
        return false;
    }

    /**  Updating hospital admin staff's details */
    @Override
    public void update(HospitalAdmin t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(HospitalAdmin t) {
        // TODO Auto-generated method stub

    }

    /**  Viewing hospital admin staff by searching hospial eircode by
        joining hadmin database & users database to create a view  */
    public HospitalAdmin getByEircode(String eirCode) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from getAdmin where eirCode = ?");
            stmt.setString(1, eirCode);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return processResultSet(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**  setting hosptial admin  staff information in the 
     * HospitalAdmin database */
    protected HospitalAdmin processResultSet(ResultSet rs) {
        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        try {
            hospitalAdmin.setContactNum(rs.getString("contactNo"));
            hospitalAdmin.setFirstName(rs.getString("firstName"));
            hospitalAdmin.setLastName(rs.getString("lastName"));
            hospitalAdmin.setPps(rs.getString("pps"));
            hospitalAdmin.setRole(rs.getString("role"));
            hospitalAdmin.setEmail(rs.getString("email"));
            hospitalAdmin.setGender(rs.getString("gender"));
            hospitalAdmin.setisactive(rs.getBoolean("isactive"));
            hospitalAdmin.setHospital(new Hospital(rs.getString("eirCode")));

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return hospitalAdmin;
    }

}
