package com.haulmont.model.entities;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {

    public enum Prior{NORMAL, CITO, STATIM;

        public static Prior get(int i){
            if (i >= 0 && i < values().length)
                return values()[i];

            return null;
        }

        public String toString(){
            switch (this){

                case NORMAL:
                    return "NORMAL";
                case CITO:
                    return "CITO";
                case STATIM:
                    return "STATIM";
            }

            return "";
        }
    }

    private long id;
    private String description;
    private long doctorID;
    private long patientID;
    private java.util.Date creationDate;
    private int validity;
    private Prior prior;

    public Receipt(long id, String description, long doctorID, long patientID, java.sql.Date creationDate, int validity, Prior prior) {
        this.id = id;
        this.description = description;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.creationDate = creationDate;
        this.validity = validity;
        this.prior = prior;
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

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public Prior getPrior() {
        return prior;
    }

    public void setPrior(Prior prior) {
        this.prior = prior;
    }

    @Override
    public String toString() {

        return description.substring(0,description.length() > 12 ? 12 : description.length()-1 )
                + "... " + (new SimpleDateFormat("dd-MM-yyyy")).format(creationDate)
                + "  " + prior.toString();
    }
}
