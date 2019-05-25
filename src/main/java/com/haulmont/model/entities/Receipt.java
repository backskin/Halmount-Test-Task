package com.haulmont.model.entities;
import java.util.Date;

public class Receipt {

    public enum Prior{NORMAL, CITO, STATIM;

        public static Prior get(int i){
            if (i >= 0 && i < values().length)
                return values()[i];

            return null;
        }
    }

    private long id;
    private String description;
    private long doctorID;
    private long patientID;
    private java.util.Date creationDate;
    private int expiration;
    private Prior prior;

    public Receipt(long id, String description, long doctorID, long patientID, java.sql.Date creationDate, int expiration, int prior) {
        this.id = id;
        this.description = description;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.creationDate = creationDate;
        this.expiration = expiration;
        this.prior = Prior.get(prior);
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    public long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public Prior getPrior() {
        return prior;
    }

    public void setPrior(Prior prior) {
        this.prior = prior;
    }
}
