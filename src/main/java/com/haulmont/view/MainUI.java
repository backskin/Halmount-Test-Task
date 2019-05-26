package com.haulmont.view;

import com.haulmont.controller.DataFilter;
import com.haulmont.controller.DataService;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Human;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.text.SimpleDateFormat;
import java.util.List;

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

    private void updateDoctors(Grid<Doctor> doctorGrid){

        doctorGrid.setItems(DataService.getDoctors());
    }

    private void updatePatients(Grid<Patient> patientGrid){

        patientGrid.setItems(DataService.getPatients());
    }

    private void updateReceipts(Grid<Receipt> receiptGrid){

        receiptGrid.setItems(DataService.getReceipts());
    }

    private void initDoctorsTable(TabSheet tabSheet){

        Grid<Doctor> doctorGrid = new Grid<>();

        doctorGrid.setItems(DataService.getDoctors());
        doctorGrid.addColumn(Human::getFullName).setCaption("Name");
        doctorGrid.addColumn(Doctor::getSpeciality).setCaption("Speciality");

        doctorGrid.setSizeFull();

        Layout functionalLayout = new HorizontalLayout();

        Button statButton = new Button ("Show Stat-s");

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button delButton = new Button("Delete");

        TextField filterField = new TextField();
        filterField.setPlaceholder("filter by name");

        Button filterButton = new Button("Filter");

        filterButton.addClickListener(clickEvent ->
                doctorGrid.setItems(DataService.findDoctor(filterField.getValue())));

        addButton.addClickListener(clickEvent -> {

            HumanEditDialog dialog = new HumanEditDialog("Add new Doctor");
            dialog.asNewDoctor();
            addWindow(dialog);

            dialog.getOk().addClickListener(clickEvent1 -> {

                if (dialog.isAccepted()) {

                    updateDoctors(doctorGrid);
                }
            });
        });

        editButton.addClickListener(clickEvent -> {

            if ((doctorGrid.getSelectionModel().getFirstSelectedItem().isPresent())){

                HumanEditDialog dialog = new HumanEditDialog("Edit Doctor");
                dialog.asEditDoctor(doctorGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);

                dialog.getOk().addClickListener(clickEvent1 -> {

                    if (dialog.isAccepted()) {

                        updateDoctors(doctorGrid);
                    }
                });
            }
        });

        delButton.addClickListener(clickEvent -> {

            if ((doctorGrid.getSelectionModel().getFirstSelectedItem().isPresent())) {

                DeleteDialog dialog = new DeleteDialog(doctorGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);

                dialog.getOk().addClickListener(clickEvent1 -> {

                    if (dialog.isAccepted()) {

                        updateDoctors(doctorGrid);
                    }
                });
            }
        });

        statButton.addClickListener(clickEvent -> addWindow(new StatsDialog()));

        functionalLayout.addComponents(statButton, addButton,editButton,delButton,
                filterField,filterButton);


        Layout l = new VerticalLayout();

        l.addComponent(functionalLayout);
        l.addComponent(doctorGrid);

        tabSheet.addTab(l).setCaption("Doctors");
    }

    private void initPatientsTable(TabSheet tabSheet){

        Grid<Patient> patientGrid = new Grid<>();
        patientGrid.setItems(DataService.getPatients());
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

            dialog.getOk().addClickListener(clickEvent1 -> {

                if (dialog.isAccepted()) {

                    updatePatients(patientGrid);
                }
            });
        });

        editButton.addClickListener(clickEvent -> {

            if (patientGrid.getSelectionModel().getFirstSelectedItem().isPresent()) {

                HumanEditDialog dialog = new HumanEditDialog("Edit Patient");
                dialog.asEditPatient(patientGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);

                dialog.getOk().addClickListener(clickEvent1 -> {

                    if (dialog.isAccepted()) {

                        updatePatients(patientGrid);
                    }
                });
            }
        });

        delButton.addClickListener(clickEvent -> {

            if (patientGrid.getSelectionModel().getFirstSelectedItem().isPresent()) {

                DeleteDialog dialog = new DeleteDialog(patientGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);

                dialog.getOk().addClickListener(clickEvent1 -> {

                    if (dialog.isAccepted()) {

                        updatePatients(patientGrid);
                    }
                });
            }
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
        receiptGrid.addColumn(Receipt::getDescription).setCaption("Description");
        receiptGrid.addColumn(r -> DataService.getDoctorByID(r.getDoctorID()).getFullName()).setCaption("Doctor");
        receiptGrid.addColumn(r -> DataService.getPatientByID(r.getPatientID()).getFullName()).setCaption("Patient");
        receiptGrid.addColumn(r -> (new SimpleDateFormat("yyyy-MM-dd")).format(r.getCreationDate())).setCaption("Creation Date");
        receiptGrid.addColumn(Receipt::getValidity).setCaption("Validity (days)");
        receiptGrid.addColumn(Receipt::getPrior).setCaption("Priority");

        receiptGrid.setSizeFull();

        Layout functionalLayout = new HorizontalLayout();

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button delButton = new Button("Delete");

        addButton.addClickListener(clickEvent -> {

            ReceiptEditDialog dialog = new ReceiptEditDialog();
            addWindow(dialog);

            dialog.getOk().addClickListener(clickEvent1 -> {

                if (dialog.isAccepted()) {

                    updateReceipts(receiptGrid);
                }
            });
        });

        editButton.addClickListener(clickEvent -> {

            if (receiptGrid.getSelectionModel().getFirstSelectedItem().isPresent()){

                ReceiptEditDialog dialog = new ReceiptEditDialog(receiptGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);


                dialog.getOk().addClickListener(clickEvent1 -> {

                    if (dialog.isAccepted()) {

                        updateReceipts(receiptGrid);
                    }
                });
            }
        });

        delButton.addClickListener(clickEvent -> {

            if (receiptGrid.getSelectionModel().getFirstSelectedItem().isPresent()){

                DeleteDialog dialog = new DeleteDialog(receiptGrid.getSelectionModel().getFirstSelectedItem().get());
                addWindow(dialog);


                dialog.getOk().addClickListener(clickEvent1 -> {

                    if (dialog.isAccepted()) {

                        updateReceipts(receiptGrid);
                    }
                });
            }
        });

        TextField descriptFilterField = new TextField();
        descriptFilterField.setPlaceholder("find description...");
        TextField doctorFilterField = new TextField();
        doctorFilterField.setPlaceholder("find doctor...");
        TextField patientFilterField = new TextField();
        patientFilterField.setPlaceholder("find patient...");

        NativeSelect<Receipt.Prior> priorListSelect = new NativeSelect<>();
        priorListSelect.setItems(Receipt.Prior.values());

        Button filterButton = new Button("Filter");

        filterButton.addClickListener(clickEvent -> {

            List<Receipt> out = DataService.getReceipts();

            if (!descriptFilterField.isEmpty()){
                out = DataFilter.filterByDescription(out,descriptFilterField.getValue());
            }

            if (!doctorFilterField.isEmpty()){
                out = DataFilter.filterByDoctor(out, doctorFilterField.getValue());
            }

            if (!patientFilterField.isEmpty()){
                out = DataFilter.filterByPatient(out, patientFilterField.getValue());
            }

            if (priorListSelect.getSelectedItem().isPresent()){

                Receipt.Prior selectedPrior = priorListSelect.getSelectedItem().get();
                out = DataFilter.filterByPriority(out, selectedPrior);
            }

            receiptGrid.setItems(out);

        });

        functionalLayout.addComponents(addButton,editButton,delButton,
                new Label("<hr />",ContentMode.HTML),
                descriptFilterField,doctorFilterField,patientFilterField,priorListSelect,filterButton);

        Layout l = new VerticalLayout();
        l.addComponent(functionalLayout);
        l.addComponent(receiptGrid);

        tabSheet.addTab(l).setCaption("Receipts");
    }
}