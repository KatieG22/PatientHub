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

    private static Connection con = Database.getConnection(new ProdDbConfig());

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

    public Optional<HospitalAdmin> getAdminByPPS(String pps) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from  getAdmin where pps = ?");
            stmt.setString(1, pps);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getRow() != 0) {
                return Optional.ofNullable(processResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(null);

    }

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

        }
        return false;
    }

    @Override
    public void update(HospitalAdmin t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(HospitalAdmin t) {
        // TODO Auto-generated method stub

    }

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

    protected HospitalAdmin processResultSet(ResultSet rs) {
        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        try {
            hospitalAdmin.setContactNum(rs.getString("contactNo"));
            hospitalAdmin.setFirstName(rs.getString("firstName"));
            hospitalAdmin.setLastName(rs.getString("lastName"));
            hospitalAdmin.setPps(rs.getString("pps"));
            hospitalAdmin.setEmail(rs.getString("email"));
            hospitalAdmin.setGender(rs.getString("gender"));
            hospitalAdmin.setisactive(rs.getBoolean("isactive"));
            hospitalAdmin.setHospital(new Hospital(rs.getString("hospital")));

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return hospitalAdmin;
    }

}
