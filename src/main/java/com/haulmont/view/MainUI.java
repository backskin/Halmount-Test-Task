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

import java.text.SimpleDateFormat;

@Theme(ValoTheme.THEME_NAME)

public class MainUI extends UI {

    @Override

    protected void init(VaadinRequest request) {

        Layout layout = new HorizontalLayout();

        TabSheet tabSheet = new TabSheet();

        Grid<Doctor> doctorGrid = new Grid<>();
        doctorGrid.setItems(DataService.getDoctors());
        doctorGrid.addColumn(Human::getId).setCaption("ID");
        doctorGrid.addColumn(Human::getFullName).setCaption("Name");
        doctorGrid.addColumn(Doctor::getSpeciality).setCaption("Speciality");

        Grid<Patient> patientGrid = new Grid<>();
        patientGrid.setItems(DataService.getPatients());
        patientGrid.addColumn(Human::getId).setCaption("ID");
        patientGrid.addColumn(Human::getFullName).setCaption("Name");
        patientGrid.addColumn(Patient::getPhone).setCaption("Phone");

        Grid<Receipt> receiptGrid = new Grid<>();
        receiptGrid.setItems(DataService.getReceipts());
        receiptGrid.addColumn(Receipt::getId).setCaption("ID");
        receiptGrid.addColumn(Receipt::getDescription).setCaption("Description");
        receiptGrid.addColumn(r -> DataService.getDoctorByID(r.getDoctorID()).getFullName()).setCaption("Doctor");
        receiptGrid.addColumn(r -> DataService.getPatientByID(r.getPatientID()).getFullName()).setCaption("Patient");
        receiptGrid.addColumn(r -> (new SimpleDateFormat("dd-MM-yyyy")).format(r.getCreationDate())).setCaption("Creation Date");
        receiptGrid.addColumn(Receipt::getValidity).setCaption("Validity (days)");
        receiptGrid.addColumn(Receipt::getPrior).setCaption("Priority");

        tabSheet.addTab(doctorGrid).setCaption("Doctors");
        tabSheet.addTab(patientGrid).setCaption("Patients");
        tabSheet.addTab(receiptGrid).setCaption("Receipts");

        doctorGrid.setSizeFull();
        patientGrid.setSizeFull();
        receiptGrid.setSizeFull();
        tabSheet.setSizeFull();

        layout.addComponent(tabSheet);
        layout.setSizeFull();

        setContent(layout);
    }
}