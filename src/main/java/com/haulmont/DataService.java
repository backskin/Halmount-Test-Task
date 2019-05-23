package com.haulmont;

import com.haulmont.db.DoctorsData;
import com.haulmont.db.PatientsData;
import com.haulmont.db.ReceiptsData;
import com.haulmont.model.Doctor;

import java.util.List;

class DataService {

    private DoctorsData doctorsData = new DoctorsData();
    private PatientsData patientsData = new PatientsData();
    private ReceiptsData receiptsData = new ReceiptsData();

    public List<Doctor> getDoctors(){

        return doctorsData.getAll();
    }

}
