package com.haulmont.controller;

import com.haulmont.model.db.DoctorsDBController;
import com.haulmont.model.db.PatientsDBController;
import com.haulmont.model.db.ReceiptsDBController;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import java.util.List;

public class DataService {

    private DoctorsDBController doctorsDBController = new DoctorsDBController();
    private PatientsDBController patientsDBController = new PatientsDBController();
    private ReceiptsDBController receiptsDBController = new ReceiptsDBController();

    public List<Doctor> getDoctors(){

        return doctorsDBController.getAll();
    }

    public List<Patient> getPatients(){

        return patientsDBController.getAll();
    }

    public List<Receipt> getReceipts(){

        return receiptsDBController.getAll();
    }

    public Doctor getDoctorByID(long id){

        return doctorsDBController.getEntityById(id);
    }

    public Patient getPatientByID(long id){

        return patientsDBController.getEntityById(id);
    }

    public Receipt getReceiptByID(long id){

        return receiptsDBController.getEntityById(id);
    }

    public void addDoctor(String firstName, String lastName, String dadsName, String spec){

        doctorsDBController.create(new Doctor(0, firstName, lastName, dadsName, spec));
    }

    public void addPatient(String firstName, String lastName, String dadsName, String phone){

        patientsDBController.create(new Patient(0, firstName, lastName, dadsName, phone));
    }

    public void addReceipt(String description, Doctor doctor, Patient patient,
                           java.sql.Date creationDate, int expiration, int prior){

        receiptsDBController.create(new Receipt(
                0, description, doctor.getId(), patient.getId(),
                creationDate, expiration, prior));

    }

    public void updateDoctor(Doctor doctor){

        doctorsDBController.update(doctor);
    }

    public void updatePatient(Patient patient){

        patientsDBController.update(patient);
    }

    public void updateReceipt(Receipt receipt){

        receiptsDBController.update(receipt);
    }

    public void deleteDoctor(Doctor doctor) throws Exception {

        if (!doctorsDBController.delete(doctor.getId()))

            throw new Exception("Cannot delete doctor " + doctor.getLastName()
                    + "because of enlisting in one of extisting receipts!"
            );
    }

    public void deletePatient(Patient patient) throws Exception {

            if (!patientsDBController.delete(patient.getId()))

                throw new Exception("Cannot delete patient " + patient.getLastName()
                        + "because of enlisting in one of extisting receipts!"
                );
    }

    public void deleteReceipt(Receipt receipt){

        receiptsDBController.delete(receipt.getId());
    }

    public List<Receipt> getDoctorReceipts(Doctor doctor){

        return receiptsDBController.chargeOf(doctor.getId());
    }
}
