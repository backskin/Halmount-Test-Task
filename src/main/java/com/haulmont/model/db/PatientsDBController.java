package com.haulmont.model.db;

import com.haulmont.model.entities.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientsDBController extends AbstractDBController<Patient, Long> {

    public PatientsDBController(){ super(); }

    @Override
    protected void addResultsToList(ResultSet rs, List<Patient> out) throws SQLException {

        while (rs.next()){

            out.add( new Patient(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5))
            );
        }
    }

        public List<Patient> find(String excerpt){

        List<Patient> out = new ArrayList<>();

        try {
            ResultSet rs;
            if (excerpt.isEmpty())
                rs = sendQuery("SELECT * FROM patients");

            else
                rs = sendQuery("SELECT * FROM patients WHERE " +
                        "LOWER(firstname) LIKE LOWER('%" + excerpt +
                        "%') OR LOWER(lastname) LIKE LOWER('%" + excerpt +
                        "%') OR LOWER(dadsname) LIKE LOWER('%" + excerpt + "%')");

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    @Override
    public List<Patient> getAll() {

        List<Patient> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM patients");

           addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }

    @Override
    public Patient getEntityById(Long id) {

        try {
            ResultSet rs = sendQuery("SELECT * FROM patients WHERE id = " + id);

            if (rs.next())
                return new Patient(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                );

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void update(Patient entity) {

        try {
            sendQuery("UPDATE patients SET "
                    + "firstname = '" + entity.getFirstName()
                    + "', lastname = '" + entity.getLastName()
                    + "', dadsname = '" + entity.getDadsName()
                    + "', phone = '" + entity.getPhone()
                    + "' WHERE id = " + entity.getId()
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {

        try {
            sendQuery("DELETE FROM patients WHERE id = " + id);

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Patient entity) {

        try {
            sendQuery("INSERT INTO patients (firstname, lastname, dadsname, phone) VALUES ('"
                    + entity.getFirstName() + "', '"
                    + entity.getLastName() + "', '"
                    + entity.getDadsName() + "', '"
                    + entity.getPhone() + "')"
            );

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
