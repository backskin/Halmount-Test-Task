package com.haulmont;

import com.haulmont.db.MySingletonDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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

            rs.close();
            rs = st.executeQuery("select * from receipts");
            while (rs.next()){
                System.out.println("ID: " + rs.getInt(1)
                        + ", desc: " + rs.getString(2)
                        + ", creat.date: " + rs.getDate(5)
                        + ", exp days: " + rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
