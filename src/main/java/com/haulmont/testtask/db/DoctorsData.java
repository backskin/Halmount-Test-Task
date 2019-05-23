package com.haulmont.testtask.db;

import com.haulmont.testtask.model.Doctor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorsData extends AbstractDBController<Doctor, Long> {

    public DoctorsData(){
        super("doctors");
    }

    @Override
    public List<Doctor> getAll() {

        List<Doctor> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM doctors");

            while (rs.next()){
                out.add(new Doctor(
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
    public Doctor getEntityById(Long id) {
        return null;
    }

    @Override
    public Doctor update(Doctor entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(Doctor entity) {
        return false;
    }
}
