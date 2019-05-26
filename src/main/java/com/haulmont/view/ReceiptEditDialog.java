package com.haulmont.view;

import com.haulmont.controller.DataService;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

class ReceiptEditDialog extends Window {

    private TextArea description = new TextArea("description");
    private NativeSelect<Doctor> docSelect = new NativeSelect<>("Doctor: ");
    private NativeSelect<Patient> patientSelect = new NativeSelect<>("Patient: ");

    private List<Doctor> doctorList = DataService.getDoctors();
    private List<Patient> patientList = DataService.getPatients();

    private DateField dateField = new DateField("creation date: ");
    private TextField validationField = new TextField("validation time (in days): ");
    private NativeSelect<Receipt.Prior> priorSelect = new NativeSelect<>("Priority:");

    private Button ok = new Button("OK");

    private VerticalLayout verticalLayout = new VerticalLayout();

    private boolean accepted = false;

    public boolean isAccepted() {
        return accepted;
    }

    public Button getOk() {
        return ok;
    }

    private void init(){

        setModal(true);
        setResizable(false);

        description.setPlaceholder("put receipt here...");

        docSelect.setItems(doctorList);
        patientSelect.setItems(patientList);
        dateField.setValue(LocalDate.now());
        priorSelect.setItems(Receipt.Prior.values());
        validationField.setPlaceholder("days before expired");

        VerticalLayout leftLayout = new VerticalLayout(docSelect,patientSelect,description);
        VerticalLayout rightLayout = new VerticalLayout(dateField,validationField,priorSelect);

        HorizontalLayout centerLayout = new HorizontalLayout(leftLayout, rightLayout);

        Button cancel = new Button("CANCEL");
        HorizontalLayout hLayout = new HorizontalLayout(ok, cancel);

        verticalLayout.addComponents(centerLayout, hLayout);

        verticalLayout.setComponentAlignment(centerLayout, Alignment.TOP_CENTER);
        verticalLayout.setComponentAlignment(hLayout, Alignment.BOTTOM_CENTER);

        cancel.addClickListener(clickEvent -> close());

        setContent(verticalLayout);
    }

    private void showError(String msg){

        Label errorLabel = new Label(msg);
        verticalLayout.addComponent(errorLabel,0);

        verticalLayout.removeComponent(errorLabel);
    }

    private boolean wrongValues(){

        try {

            Integer.parseInt(validationField.getValue());

            if (dateField.getValue().isBefore(LocalDate.of(1991, 1, 1))
                    || dateField.getValue().isAfter(LocalDate.now()))
                throw new Exception("Неверная дата!");

            if (!patientSelect.getSelectedItem().isPresent()
                    || !docSelect.getSelectedItem().isPresent()
                    || !priorSelect.getSelectedItem().isPresent()
                    || description.isEmpty()){

                throw new Exception("Не введены все значения!");
            }

            return false;

        } catch (Exception e){

            showError(e.getLocalizedMessage());
            return true;
        }
    }

    private Receipt getEntity(long id){

        return new Receipt(id,
                description.getValue(),
                docSelect.getSelectedItem().get().getId(),
                patientSelect.getSelectedItem().get().getId(),
                Date.valueOf(dateField.getValue()),
                Integer.parseInt(validationField.getValue()),
                priorSelect.getSelectedItem().get());
    }

    ReceiptEditDialog(){

        super("New Receipt");
        init();

        ok.addClickListener(clickEvent -> {

            if (wrongValues()) return;

            DataService.addReceipt(getEntity(0));

            accepted = true;
            close();
        });
    }

    ReceiptEditDialog(Receipt receipt){

        super("Edit Receipt");
        init();

        description.setValue(receipt.getDescription());

        for (Doctor d: doctorList) {

            if (d.getId() == receipt.getDoctorID()){

                docSelect.setSelectedItem(d);
                break;
            }
        }

        for (Patient p: patientList) {

            if (p.getId() == receipt.getPatientID()){

                patientSelect.setSelectedItem(p);
                break;
            }
        }

        dateField.setValue(new Date(receipt.getCreationDate().getTime()).toLocalDate());
        priorSelect.setSelectedItem(Receipt.Prior.get(receipt.getPrior().ordinal()));
        validationField.setValue(String.valueOf(receipt.getValidity()));

        ok.addClickListener(clickEvent -> {

            if (wrongValues()) return;

            DataService.updateReceipt(getEntity(receipt.getId()));

            accepted = true;
            close();
        });
    }
}
