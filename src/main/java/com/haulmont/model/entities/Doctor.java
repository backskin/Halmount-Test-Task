package com.haulmont.model.entities;

public class Doctor extends Human{

    private String speciality;

    public Doctor(long id, String firstName, String lastName, String dadsName, String spec) {
        super(id, firstName, lastName, dadsName);
        speciality = spec;
    }

    public String getSpeciality() {
        return speciality;
    }

    void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String toString(){

        return getLastName() + " " + getFirstName().charAt(0) + ". " + getDadsName().charAt(0) + ". " + speciality;
    }
}
