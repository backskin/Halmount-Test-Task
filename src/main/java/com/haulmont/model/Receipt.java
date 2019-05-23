package com.haulmont.model;

import java.util.Date;

public class Receipt {

    public enum Prior{NORMAL, CITO, STATIM;

        Prior get(int i){
            if (i >= 0 && i < values().length)
                return values()[i];

            return null;
        }
    }

    private String description;
    private Patient patient;
    private Doctor doctor;
    private Date creationDate;
    private Date expirationDate;
    private Prior prior;

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Prior getPrior() {
        return prior;
    }

    void setPrior(Prior prior) {
        this.prior = prior;
    }
}
