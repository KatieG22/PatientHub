package com.patienthub.model;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import com.patienthub.utils.Database;

import org.junit.Test;

public class DatabaseTest {
    @Test
    public void testDbEstablishedConnection() {

        Connection connection = Database.getConnection();
        boolean isConnectionOpen = false;
        try {
            isConnectionOpen = !connection.isClosed();
        } catch (SQLException e) {
            // do noting
        }
        assertTrue(isConnectionOpen);
    }

}
