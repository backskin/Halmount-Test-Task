package com.haulmont.testtask;

import com.haulmont.testtask.db.DoctorsData;
import com.haulmont.testtask.db.PatientsData;
import com.haulmont.testtask.db.ReceiptsData;
import com.haulmont.testtask.model.Doctor;

import java.util.List;

class DataService {

    private DoctorsData doctorsData = new DoctorsData();
    private PatientsData patientsData = new PatientsData();
    private ReceiptsData receiptsData = new ReceiptsData();

    public List<Doctor> getDoctors(){

        return doctorsData.getAll();
    }

}
