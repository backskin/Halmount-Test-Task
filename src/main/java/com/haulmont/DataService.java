package com.haulmont;

import com.haulmont.db.DoctorsData;
import com.haulmont.db.PatientsData;
import com.haulmont.db.ReceiptsData;
import com.haulmont.model.Doctor;
import com.haulmont.model.Patient;
import com.haulmont.model.Receipt;
import java.util.List;

class DataService {

    private DoctorsData doctorsData = new DoctorsData();
    private PatientsData patientsData = new PatientsData();
    private ReceiptsData receiptsData = new ReceiptsData();

    public List<Doctor> getDoctors(){

        return doctorsData.getAll();
    }

    public List<Patient> getPatients(){

        return patientsData.getAll();
    }

    public List<Receipt> getReceipts(){

        return receiptsData.getAll();
    }

    public Doctor getDoctorByID(long id){

        return doctorsData.getEntityById(id);
    }

    public Patient getPatientByID(long id){

        return patientsData.getEntityById(id);
    }

    public Receipt getReceiptByID(long id){

        return receiptsData.getEntityById(id);
    }

    public void addDoctor(String firstName, String lastName, String dadsName, String spec){

        doctorsData.create(new Doctor(0, firstName, lastName, dadsName, spec));
    }

    public void addPatient(String firstName, String lastName, String dadsName, String phone){

        patientsData.create(new Patient(0, firstName, lastName, dadsName, phone));
    }

    public void addReceipt(String description, Doctor doctor, Patient patient,
                           java.sql.Date creationDate, int expiration, int prior){

        receiptsData.create(new Receipt(
                0, description, doctor.getId(), patient.getId(),
                creationDate, expiration, prior));

    }

    public void updateDoctor(Doctor doctor){

        doctorsData.update(doctor);
    }

    public void updatePatient(Patient patient){

        patientsData.update(patient);
    }

    public void updateReceipt(Receipt receipt){

        receiptsData.update(receipt);
    }

    public void deleteDoctor(Doctor doctor) throws Exception {

        if (!doctorsData.delete(doctor.getId()))

            throw new Exception("Cannot delete doctor " + doctor.getLastName()
                    + "because of enlisting in one of extisting receipts!"
            );
    }

    public void deletePatient(Patient patient) throws Exception {

            if (!patientsData.delete(patient.getId()))

                throw new Exception("Cannot delete patient " + patient.getLastName()
                        + "because of enlisting in one of extisting receipts!"
                );
    }

    public void deleteReceipt(Receipt receipt){

        receiptsData.delete(receipt.getId());
    }

    public List<Receipt> getDoctorReceipts(Doctor doctor){

        return receiptsData.chargeOf(doctor.getId());
    }
}
