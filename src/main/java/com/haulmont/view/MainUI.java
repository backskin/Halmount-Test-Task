package com.haulmont.view;

import com.haulmont.controller.DataService;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Human;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.text.SimpleDateFormat;

@Theme(ValoTheme.THEME_NAME)

public class MainUI extends UI {

    @Override

    protected void init(VaadinRequest request) {

        TabSheet tabSheet = new TabSheet();

        initDoctorsTable(tabSheet);
        initPatientsTable(tabSheet);
        initReceiptsTable(tabSheet);

        tabSheet.setSizeFull();

        setContent(tabSheet);
    }

    private void initDoctorsTable(TabSheet tabSheet){

        Grid<Doctor> doctorGrid = new Grid<>();

        doctorGrid.setItems(DataService.getDoctors());
        doctorGrid.addColumn(Human::getId).setCaption("ID");
        doctorGrid.addColumn(Human::getFullName).setCaption("Name");
        doctorGrid.addColumn(Doctor::getSpeciality).setCaption("Speciality");

        doctorGrid.setSizeFull();

        Layout functionalLayout = new HorizontalLayout();

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button delButton = new Button("Delete");
        TextField filterField = new TextField();
        filterField.setPlaceholder("filter by name");
        Button acceptFilterButton = new Button("Filter");

        acceptFilterButton.addClickListener(clickEvent ->
                doctorGrid.setItems(DataService.findDoctor(filterField.getValue())));

        addButton.addClickListener(clickEvent -> {

            HumanEditDialog dialog = new HumanEditDialog("Add new Doctor");
            dialog.asNewDoctor();
            addWindow(dialog);
        });

        editButton.addClickListener(clickEvent -> {

            if (!(doctorGrid.getSelectedItems().isEmpty())){

                HumanEditDialog dialog = new HumanEditDialog("Edit Doctor");
                dialog.asEditDoctor(doctorGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);
            }
        });

        delButton.addClickListener(clickEvent -> {

        });

        functionalLayout.addComponents(addButton,editButton,delButton,filterField,acceptFilterButton);

        Layout l = new VerticalLayout();

        l.addComponent(functionalLayout);
        l.addComponent(doctorGrid);

        tabSheet.addTab(l).setCaption("Doctors");
    }

    private void initPatientsTable(TabSheet tabSheet){

        Grid<Patient> patientGrid = new Grid<>();
        patientGrid.setItems(DataService.getPatients());
        patientGrid.addColumn(Human::getId).setCaption("ID");
        patientGrid.addColumn(Human::getFullName).setCaption("Name");
        patientGrid.addColumn(Patient::getPhone).setCaption("Phone");

        patientGrid.setSizeFull();

        Layout functionalLayout = new HorizontalLayout();

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button delButton = new Button("Delete");
        TextField filterField = new TextField();
        filterField.setPlaceholder("filter by name");
        Button acceptFilterButton = new Button("Filter");

        acceptFilterButton.addClickListener(clickEvent ->
                patientGrid.setItems(DataService.findPatient(filterField.getValue())));

        addButton.addClickListener(clickEvent -> {

            HumanEditDialog dialog = new HumanEditDialog("Add new Patient");
            dialog.asNewPatient();
            addWindow(dialog);
        });

        editButton.addClickListener(clickEvent -> {

            if (!patientGrid.getSelectedItems().isEmpty()) {

                HumanEditDialog dialog = new HumanEditDialog("Edit Patient");
                dialog.asEditPatient(patientGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);
            }
        });

        delButton.addClickListener(clickEvent -> {

        });

        functionalLayout.addComponents(addButton,editButton,delButton,filterField,acceptFilterButton);

        Layout l = new VerticalLayout();
        l.addComponent(functionalLayout);
        l.addComponent(patientGrid);

        tabSheet.addTab(l).setCaption("Patients");
    }

    private void initReceiptsTable(TabSheet tabSheet){

        Grid<Receipt> receiptGrid = new Grid<>();
        receiptGrid.setItems(DataService.getReceipts());
        receiptGrid.addColumn(Receipt::getId).setCaption("ID");
        receiptGrid.addColumn(Receipt::getDescription).setCaption("Description");
        receiptGrid.addColumn(r -> DataService.getDoctorByID(r.getDoctorID()).getFullName()).setCaption("Doctor");
        receiptGrid.addColumn(r -> DataService.getPatientByID(r.getPatientID()).getFullName()).setCaption("Patient");
        receiptGrid.addColumn(r -> (new SimpleDateFormat("dd-MM-yyyy")).format(r.getCreationDate())).setCaption("Creation Date");
        receiptGrid.addColumn(Receipt::getValidity).setCaption("Validity (days)");
        receiptGrid.addColumn(Receipt::getPrior).setCaption("Priority");

        receiptGrid.setSizeFull();

        Layout functionalLayout = new HorizontalLayout();
        functionalLayout.addComponents(new Button("Add"), new Button("Edit"), new Button("Delete"));

        Layout l = new VerticalLayout();
        l.addComponent(functionalLayout);
        l.addComponent(receiptGrid);

        tabSheet.addTab(l).setCaption("Receipts");
    }
}