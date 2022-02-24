package com.patienthub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            System.out.println(e.getMessage() + "<---- cause");

        }
        return false;
    }

    @Override
    public void update(HospitalAdmin t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(HospitalAdmin t) {
        // TODO Auto-generated method stub

    }

}
