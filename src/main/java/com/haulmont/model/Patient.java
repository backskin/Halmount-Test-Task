package com.haulmont.model;

public class Patient extends Human {

    private String phone;

    public Patient(long id, String firstName, String lastName, String dadsName) {
        super(id, firstName, lastName, dadsName);
    }

    public String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }
}
