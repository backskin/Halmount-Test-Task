package com.haulmont.model.entities;

public class Patient extends Human {

    private String  phone;

    public Patient(long id, String firstName, String lastName, String dadsName, String phone) {
        super(id, firstName, lastName, dadsName);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString(){
        return getLastName() + " " + getFirstName().charAt(0) + ". " + getDadsName().charAt(0) + ".  t: " + phone;
    }
}
