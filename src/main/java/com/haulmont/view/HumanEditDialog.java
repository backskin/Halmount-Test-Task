package com.haulmont.view;

import com.haulmont.controller.DataService;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Patient;
import com.vaadin.ui.*;

class HumanEditDialog extends Window {

    private VerticalLayout subContent = new VerticalLayout();

    private TextField lNameField = new TextField("Last Name");
    private TextField fNameField = new TextField("First Name");
    private TextField dNameField = new TextField("Second Name");
    private TextField specField = new TextField("Specialization");
    private TextField phoneField = new TextField("Phone number");

    private Button ok = new Button("OK");

    private boolean accepted = false;

    boolean isAccepted() {
        return accepted;
    }

    Button getOk() {
        return ok;
    }

    private Doctor getDoctor(long id){

        return new Doctor(
                id,
                fNameField.getValue(),
                lNameField.getValue(),
                dNameField.getValue(),
                specField.getValue()
        );
    }

    private Patient getPatient(long id){

        return new Patient(
                id,
                fNameField.getValue(),
                lNameField.getValue(),
                dNameField.getValue(),
                phoneField.getValue()
        );
    }

    private boolean wrongValues(){

        return fNameField.isEmpty()
                || lNameField.isEmpty()
                || dNameField.isEmpty()
                || (phoneField.isEmpty() && specField.isEmpty());

    }

    HumanEditDialog(String caption) {

        super(caption);
        setModal(true);
        setResizable(false);
        setClosable(false);

        Layout h = new HorizontalLayout();
        Button cancel = new Button("CANCEL");
        h.addComponents(ok, cancel);

        subContent.addComponents(lNameField,fNameField,dNameField, h);
        subContent.setComponentAlignment(lNameField, Alignment.TOP_CENTER);
        subContent.setComponentAlignment(fNameField, Alignment.TOP_CENTER);
        subContent.setComponentAlignment(dNameField, Alignment.TOP_CENTER);
        subContent.setComponentAlignment(h, Alignment.TOP_CENTER);

        cancel.addClickListener(clickEvent -> close());
        setContent(subContent);
    }

    void asNewDoctor(){

        subContent.addComponent(specField,3);
        subContent.setComponentAlignment(specField, Alignment.TOP_CENTER);

        ok.addClickListener(clickEvent -> {

            if (wrongValues()){

                new ErrorMessage(new Exception("Addition error! Empty fields detected!"));
            } else {

                DataService.addDoctor(getDoctor(0));

                accepted = true;
                close();
            }
        });
    }

    void asEditDoctor(Doctor entity){

        subContent.addComponent(specField,3);
        subContent.setComponentAlignment(specField, Alignment.TOP_CENTER);

        lNameField.setValue(entity.getLastName());
        fNameField.setValue(entity.getFirstName());
        dNameField.setValue(entity.getDadsName());

        TextField spec = (TextField) subContent.getComponent(3);
        spec.setValue(entity.getSpeciality());

        ok.addClickListener(clickEvent -> {


            if (wrongValues()){

                new ErrorMessage(new Exception("Editing error! Empty fields detected!"));
            } else {

                DataService.updateDoctor(getDoctor(entity.getId()));

                accepted = true;
                close();
            }
        });
    }

    void asNewPatient(){

        subContent.addComponent(phoneField, 3);
        subContent.setComponentAlignment(phoneField, Alignment.TOP_CENTER);

        ok.addClickListener(clickEvent -> {

            if (wrongValues()){

                new ErrorMessage(new Exception("Addition error! Empty fields detected!"));
            } else {

                DataService.addPatient(getPatient(0));

                accepted = true;
                close();
            }
        });
    }

    void asEditPatient(Patient entity){

        subContent.addComponent(phoneField, 3);
        subContent.setComponentAlignment(phoneField, Alignment.TOP_CENTER);

        lNameField.setValue(entity.getLastName());
        fNameField.setValue(entity.getFirstName());
        dNameField.setValue(entity.getDadsName());

        TextField phone = (TextField) subContent.getComponent(3);
        phone.setValue(entity.getPhone());

        ok.addClickListener(clickEvent -> {

            if (wrongValues()){

                new ErrorMessage(new Exception("Editing error! Empty fields detected!"));
            } else {

                DataService.updatePatient(getPatient(entity.getId()));

                accepted = true;
                close();
            }
        });
    }
}
