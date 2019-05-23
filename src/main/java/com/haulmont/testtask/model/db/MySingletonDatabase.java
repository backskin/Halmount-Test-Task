package com.haulmont.testtask.model.db;
import org.hsqldb.cmdline.SqlFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySingletonDatabase {

    private static Connection c = null;

    public static Connection getConnection(){

        if (c == null) {
            try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                c = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
                setup();

            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace(System.out);
            }
        }

        return c;
    }

    private static void setup() throws SQLException, IOException {

        Statement st = c.createStatement();
        SqlFile sf = new SqlFile(new File(""));

    }
}