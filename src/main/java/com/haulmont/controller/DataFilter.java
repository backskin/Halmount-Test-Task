package com.haulmont.controller;

import com.haulmont.model.entities.Receipt;

import java.util.ArrayList;
import java.util.List;

public class DataFilter {

    public static List<Receipt> filterByDoctor(List<Receipt> receipts, String excerpt){

        if (excerpt.isEmpty()) return receipts;

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (DataService.getDoctorByID(r.getDoctorID()).contains(excerpt))

                out.add(r);
        }

        return out;
    }


    public static List<Receipt> filterByPatient(List<Receipt> receipts, String excerpt){

        if (excerpt.isEmpty()) return receipts;

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (DataService.getPatientByID(r.getPatientID()).contains(excerpt))

                out.add(r);
        }

        return out;
    }

    public static List<Receipt> filterByDescription(List<Receipt> receipts, String excerpt){

        if (excerpt.isEmpty()) return receipts;

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (r.getDescription().toLowerCase().contains(excerpt.toLowerCase()))

                out.add(r);
        }

        return out;
    }

    public static List<Receipt> filterByPriority(List<Receipt> receipts, Receipt.Prior prior){

        if (prior == null) return receipts;

        List<Receipt> out = new ArrayList<>();

        for (Receipt r: receipts) {

            if (r.getPrior().equals(prior))

                out.add(r);
        }

        return out;
    }
}
