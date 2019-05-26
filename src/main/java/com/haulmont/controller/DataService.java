package com.haulmont.controller;

import com.haulmont.model.db.DoctorsDBController;
import com.haulmont.model.db.PatientsDBController;
import com.haulmont.model.db.ReceiptsDBController;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import java.util.List;

public class DataService {

    private static DoctorsDBController doctorsDBController = new DoctorsDBController();
    private static PatientsDBController patientsDBController = new PatientsDBController();
    private static ReceiptsDBController receiptsDBController = new ReceiptsDBController();

    public static List<Doctor> getDoctors(){

        return doctorsDBController.getAll();
    }

    public static List<Patient> getPatients(){

        return patientsDBController.getAll();
    }

    public static List<Receipt> getReceipts(){

        return receiptsDBController.getAll();
    }


    public static List<Doctor> findDoctor(String excerpt){

        return doctorsDBController.find(excerpt);
    }

    public static List<Patient> findPatient(String excerpt){

        return patientsDBController.find(excerpt);
    }

    public static Doctor getDoctorByID(long id){

        return doctorsDBController.getEntityById(id);
    }

    public static Patient getPatientByID(long id){

        return patientsDBController.getEntityById(id);
    }

    public static Receipt getReceiptByID(long id){

        return receiptsDBController.getEntityById(id);
    }

    public static void addDoctor(Doctor entity){

        doctorsDBController.create(entity);
    }

    public static void addPatient(Patient entity){

        patientsDBController.create(entity);
    }

    public static void addReceipt(Receipt entity){

        receiptsDBController.create(entity);

    }

    public static void updateDoctor(Doctor doctor){

        doctorsDBController.update(doctor);
    }

    public static void updatePatient(Patient patient){

        patientsDBController.update(patient);
    }

    public static void updateReceipt(Receipt receipt){

        receiptsDBController.update(receipt);
    }

    public static void deleteDoctor(Doctor doctor) throws Exception {

        if (!doctorsDBController.delete(doctor.getId())) {

            throw new Exception("Cannot delete doctor '" + doctor.getLastName()
                    + "' because of enlisting in one of extisting receipts!"
            );
        }
    }

    public static void deletePatient(Patient patient) throws Exception {

        if (!patientsDBController.delete(patient.getId())) {

            throw new Exception("Cannot delete patient '" + patient.getLastName()
                    + "' because of enlisting in one of extisting receipts!"
            );
        }
    }

    public static void deleteReceipt(Receipt receipt) throws Exception {

        if (!receiptsDBController.delete(receipt.getId())) {

            throw new Exception("Cannot delete receipt id'" + receipt.getId() + "'!");
        }
    }

    public static List<Receipt> getDoctorReceipts(Doctor doctor){

        return receiptsDBController.filterByDoctor(doctor.getId());
    }
}
