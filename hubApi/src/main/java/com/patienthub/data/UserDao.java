package com.patienthub.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.patienthub.model.User;
import com.patienthub.utils.Database;
import com.patienthub.utils.ProdDbConfig;

public class UserDao implements Dao<User> {
    private static Connection con = Database.getConnection(new ProdDbConfig());

    @Override
    public Optional<User> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(User user) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into users (firstName, lastName, contactNo," +
                            "email,pps,gender,role)" +
                            "Values(?, ?, ?, ?, ?, ?, ? )");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getContactNum());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPps());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getRole());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage() + "<---- cause");
            return false;

        }

    }

    @Override
    public void update(User t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(User user) {
        // TODO Auto-generated method stub

        try {
            PreparedStatement stmt = con.prepareStatement("delete from users where pps= ?");
            stmt.setString(1, user.getPps());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
