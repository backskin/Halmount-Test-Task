package com.haulmont.db;

import com.haulmont.model.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientsData extends AbstractDBController<Patient, Long> {

    public PatientsData(){ super(); }

    @Override
    public List<Patient> getAll() {

        List<Patient> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM patients;");

            while (rs.next()){
                out.add(new Patient(
                        Long.parseLong(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public Patient getEntityById(Long id) {

        try {
            ResultSet rs = sendQuery("SELECT * FROM patients WHERE id = "
                    + id.toString() + ";");

            return new Patient(
                    Long.parseLong(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Patient update(Patient entity) {

        try {
            sendQuery("UPDATE patients SET "
                    + "firstname = '" + entity.getFirstName()
                    + "', lastname = '" + entity.getLastName()
                    + "', dadsname = '" + entity.getDadsName()
                    + "', phone = '" + entity.getPhone()
                    + "', WHERE id = " + entity.getId() + ";");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            sendQuery("DELETE FROM patients WHERE id = " + id + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean create(Patient entity) {

        try {
            sendQuery("INSERT INTO patients (firstname, lastname, dadsname, phone) VALUES ("
                    + entity.getFirstName() + ", "
                    + entity.getLastName() + ", "
                    + entity.getDadsName() + ", "
                    + entity.getPhone() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
