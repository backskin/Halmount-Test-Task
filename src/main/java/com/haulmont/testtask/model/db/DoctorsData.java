package com.haulmont.testtask.model.db;

import com.haulmont.testtask.model.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorsData extends AbstractDBController<Doctor, Long> {

    public DoctorsData(){
        super();
    }

    @Override
    public List<Doctor> getAll() {

        List<Doctor> out = new ArrayList<>();
        return null;
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
