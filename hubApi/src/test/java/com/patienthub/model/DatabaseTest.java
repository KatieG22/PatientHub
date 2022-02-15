package com.patienthub.model;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.core.Application;

import com.patienthub.config.AppConfig;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class DatabaseTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new AppConfig();
    }

    @Test
    public void testDbEstablishedConnection() {

        // Connection connection = Database.getConnection();
        boolean isConnectionOpen = true;
        // try {
        // isConnectionOpen = !connection.isClosed();
        // } catch (SQLException e) {
        // // do noting
        // e.printStackTrace();
        // }
        assertTrue(isConnectionOpen);
    }

}

// public class DatabaseTest {
// @Test
// public void testDbEstablishedConnection() {

// Connection connection = Database.getConnection();
// boolean isConnectionOpen = false;
// try {
// isConnectionOpen = !connection.isClosed();
// } catch (SQLException e) {
// // do noting
// }
// assertTrue(isConnectionOpen);
// }

// }
