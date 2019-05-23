package com.haulmont.testtask.model.db;
import org.hsqldb.cmdline.SqlFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySingletonDatabase {

    private Connection connection;
    private static MySingletonDatabase instance = null;

    private MySingletonDatabase(){
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public static MySingletonDatabase getInstance(){

        if (instance == null) {
            instance = new MySingletonDatabase();
            instance.setup();
        }
        return instance;
    }

    private void setup() {

        try {
            Statement st = connection.createStatement();
            SqlFile sf = new SqlFile(new File(""));

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnetion() {
        return connection;
    }
}