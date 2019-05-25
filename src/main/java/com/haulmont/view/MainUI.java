package com.haulmont.view;

import com.haulmont.controller.DataService;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Human;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)

public class MainUI extends UI {

    @Override

    protected void init(VaadinRequest request) {

        DataService dataService = new DataService();

        TabSheet tabSheet = new TabSheet();

        Grid<Doctor> doctorGrid = new Grid<>();
        doctorGrid.setItems(dataService.getDoctors());
        doctorGrid.addColumn(Human::getFullName).setCaption("Name");
        doctorGrid.addColumn(Doctor::getSpeciality).setCaption("Speciality");

        Grid<Patient> patientGrid = new Grid<>();
        patientGrid.setItems(dataService.getPatients());
        patientGrid.addColumn(Human::getFullName).setCaption("Name");
        patientGrid.addColumn(Patient::getPhone).setCaption("Phone");

        Grid<Receipt> receiptGrid = new Grid<>();
        receiptGrid.setItems(dataService.getReceipts());
        receiptGrid.addColumn(Receipt::getDescription).setCaption("Description");
        receiptGrid.addColumn(Receipt::getDoctorID).setCaption("Doctor");
        receiptGrid.addColumn(Receipt::getPatientID).setCaption("Patient");

        tabSheet.addTab(doctorGrid).setCaption("Doctors");
        tabSheet.addTab(patientGrid).setCaption("Patients");
        tabSheet.addTab(receiptGrid).setCaption("Receipts");

        doctorGrid.setSizeFull();
        patientGrid.setSizeFull();
        receiptGrid.setSizeFull();
        tabSheet.setSizeFull();

        setContent(tabSheet);
    }
}