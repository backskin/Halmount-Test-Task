package com.haulmont.testtask.model;

public abstract class Human {

    private long id;
    private String firstName;
    private String lastName;
    private String dadsName;

    Human(long id, String firstName, String lastName, String dadsName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dadsName = dadsName;
    }

    protected void setId(long id){

        this.id = id;
    }

    public long getId() {
        return id;
    }

    public boolean equals(Human h){

        return (id == h.id);
    }

    void setName(String firstName, String lastName, String dadsName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dadsName = dadsName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setDadsName(String dadsName) {
        this.dadsName = dadsName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDadsName() {
        return dadsName;
    }

    public String getFullName(){

        return lastName + " " + firstName + " " + dadsName;
    }
}
