package com.haulmont.testtask.model.entities;

public class Doctor extends Human{

    private String speciality;

    public Doctor(long id, String firstName, String lastName, String dadsName) {
        super(id, firstName, lastName, dadsName);
    }

    public String getSpeciality() {
        return speciality;
    }

    void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
