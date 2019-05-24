package com.haulmont;

import com.haulmont.model.Human;
import com.haulmont.model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class DataFilter {

    private DataService dataService;

    DataFilter(DataService dataService){

        this.dataService = dataService;
    }

    private List<Human> findHumansByExcerpt(List<Human> humans, String excerpt){

        List<Human> out = new ArrayList<>();

        for (Human h: humans) {

            if (h.contains(excerpt))

                out.add(h);
        }

        return out;
    }

    public List<Receipt> filterByDoctor(List<Receipt> receipts, String excerpt){

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (dataService.getDoctorByID(r.getDoctorID()).contains(excerpt))

                out.add(r);
        }

        return out;
    }


    public List<Receipt> filterByPatient(List<Receipt> receipts, String excerpt){

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (dataService.getPatientByID(r.getPatientID()).contains(excerpt))

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

    public List<Receipt> filterByPriority(List<Receipt> receipts, Receipt.Prior prior){

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (r.getPrior().equals(prior))

                out.add(r);
        }

        return out;
    }
}
