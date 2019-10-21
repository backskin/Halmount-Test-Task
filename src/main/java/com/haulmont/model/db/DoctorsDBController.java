package com.haulmont.model.db;

import com.haulmont.model.entities.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDBController extends AbstractDBController<Doctor, Long> {

    public DoctorsDBController() {
        super();
    }

    @Override
    protected void addResultsToList(ResultSet rs, List<Doctor> out) throws SQLException {

        while (rs.next()){

            out.add( new Doctor(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5))
            );
        }
    }


        public List<Doctor> find(String excerpt){

        List<Doctor> out = new ArrayList<>();

        try {
            ResultSet rs;
            if (excerpt.isEmpty())
                rs = sendQuery("SELECT * FROM doctors");

            else
                rs = sendQuery("SELECT * FROM doctors WHERE " +
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
    public List<Doctor> getAll() {

        List<Doctor> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM doctors");

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }

    @Override
    public Doctor getEntityById(Long id) {

        try {
            ResultSet rs = sendQuery("SELECT * FROM doctors WHERE id = " + id);

            if (rs.next())
                return new Doctor(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                );

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    @Override
    public void update(Doctor entity) {

        try {
            sendQuery("UPDATE doctors SET "
                    + "firstname = '" + entity.getFirstName()
                    + "', lastname = '" + entity.getLastName()
                    + "', dadsname = '" + entity.getDadsName()
                    + "', speciality = '" + entity.getSpeciality()
                    + "' WHERE id = " + entity.getId()
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean delete(Long id) {

        try {
            sendQuery("DELETE FROM doctors WHERE id = " + id);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Doctor entity) {

        try {
            sendQuery("INSERT INTO doctors (firstname, lastname, dadsname, speciality)" +
                    " VALUES ('"
                    + entity.getFirstName() + "', '"
                    + entity.getLastName() + "', '"
                    + entity.getDadsName() + "', '"
                    + entity.getSpeciality() + "')"
            );

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}