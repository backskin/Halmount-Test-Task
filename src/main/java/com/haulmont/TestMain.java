package com.haulmont;

import com.haulmont.model.db.SingletonDatabase;
import com.haulmont.model.entities.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMain {

    public static void main(String[] args) {

        inittest();
    }

    public static void test2(){

        SingletonDatabase database = SingletonDatabase.getInstance();
    }


    public static void inittest(){

        SingletonDatabase database = SingletonDatabase.getInstance();

        try {
            Statement st = database.createStatement();
            ResultSet rs = st.executeQuery("select * from doctors");
            while (rs.next()){
                System.out.println("ID: " + rs.getString(1)
                        + ", name: " + rs.getString(3)
                        + " " + rs.getString(2)
                        + " " + rs.getString(4));
            }

            rs.close();
            rs = st.executeQuery("select * from receipts");
            while (rs.next()){
                System.out.println("ID: " + rs.getInt(1)
                        + ", desc: " + rs.getString(2)
                        + ", creat.date: " + rs.getDate(5)
                        + ", exp days: " + rs.getInt(6)
                        + ", prior: " + Receipt.Prior.get(rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
