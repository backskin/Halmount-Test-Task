package com.haulmont.db;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class MySingletonDatabase {

    private Connection connection;
    private static MySingletonDatabase instance = null;

    private MySingletonDatabase(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
            loadDefault();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public static MySingletonDatabase getInstance(){

        if (instance == null) {
            instance = new MySingletonDatabase();
        }
        return instance;
    }

    private void loadDefault(){

        loadScriptResource("sql/dbdefault.sql");
        loadScriptResource("sql/dbstack.sql");
    }

    public void loadScriptResource(String sqlscript) {

        try {
            SqlFile sf = new SqlFile(
                    new File(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource(sqlscript)).getFile()
                    )
            );

            sf.setConnection(connection);
            sf.execute();

        } catch (SQLException | IOException | SqlToolError e) {
            e.printStackTrace();
        }
    }



    public Connection getConnection() {
        return connection;
    }
}