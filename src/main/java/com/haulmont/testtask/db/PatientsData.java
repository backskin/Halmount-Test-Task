package com.haulmont.testtask.db;

import com.haulmont.testtask.model.Patient;

import java.util.List;

public class PatientsData extends AbstractDBController<Patient, Long> {

    public PatientsData(){
        super("patients");
    }

    @Override
    public List<Patient> getAll() {
        return null;
    }

    @Override
    public Patient getEntityById(Long id) {
        return null;
    }

    @Override
    public Patient update(Patient entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(Patient entity) {
        return false;
    }
}
