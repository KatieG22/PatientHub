package com.patienthub.data;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.google.common.hash.Hashing;
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

    public User getByPpsNumber(String pps) {

        try {
            PreparedStatement stmt = con.prepareStatement("select * from users where pps=?");
            stmt.setString(1, pps);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            return processResultSet(rs);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        String password = user.getPassword();

        String hashedPasssword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        String userRole = user.getClass().getSimpleName();
        System.out.println("saving users!!!!!");
        System.out.println(userRole);
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into users (firstName, lastName, contactNo," +
                            "email,pps,gender,role,password)" +
                            "Values(?, ?, ?, ?, ?, ?, ?,? )");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getContactNum());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPps());
            stmt.setString(6, user.getGender());
            stmt.setString(7, userRole);
            stmt.setString(8, hashedPasssword);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

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

    protected User processResultSet(ResultSet rs) {
        User user = new User();
        try {
            if (rs.next() && rs.getRow() != 0) {
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setContactNum(rs.getString("contactNo"));
                user.setEmail(rs.getString("email"));
                user.setPps(rs.getString("pps"));
                user.setGender(rs.getString("gender"));
                user.setisactive(rs.getBoolean("isactive"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public User getUserbyEmail(String email) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from users where email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return processResultSet(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

}
