package com.haulmont;

import com.haulmont.model.Human;
import com.haulmont.model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class DataFilter {

    DataService service;

    DataFilter(DataService service){

        this.service = service;
    }

    private List<Human> findPatientsByQuery(List<Human> humans, String excerpt){

        for (Human h: humans) {

            if (!h.contains(excerpt)) humans.remove(h);
        }

        return humans;
    }

    public List<Receipt> filterByDoctor(List<Receipt> receipts, String excerpt){

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (service.getDoctorByID(r.getDoctorID()).contains(excerpt))
                out.add(r);
        }

        return out;
    }


    public List<Receipt> filterByPatient(List<Receipt> receipts, String excerpt){

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (service.getPatientByID(r.getPatientID()).contains(excerpt))
                out.add(r);
        }

        return out;
    }

    public List<Receipt> filterByDescription(List<Receipt> receipts, String excerpt){

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (r.getDescription().contains(excerpt))
                out.add(r);
        }

        return out;
    }
}
