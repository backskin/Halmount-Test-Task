package com.haulmont;

import com.haulmont.db.MySingletonDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMain {

    public static void main(String[] args) {

        MySingletonDatabase database = MySingletonDatabase.getInstance();

        try {
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from doctors");
            while (rs.next()){
                System.out.println("ID: " + rs.getString(1)
                        + ", name: " + rs.getString(2)
                        + " " + rs.getString(3)
                        + " " + rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
