package com.haulmont.model;

public class Patient extends Human {

    private int phone;

    public Patient(long id, String firstName, String lastName, String dadsName, int phone) {
        super(id, firstName, lastName, dadsName);
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    void setPhone(int phone) {
        this.phone = phone;
    }
}
