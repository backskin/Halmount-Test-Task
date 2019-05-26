package com.haulmont.view;

import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Patient;
import com.vaadin.ui.*;

public class HumanEditDialog extends Window {

    private VerticalLayout subContent = new VerticalLayout();

    private TextField lNameField = new TextField("Last Name");
    private TextField fNameField = new TextField("First Name");
    private TextField dNameField = new TextField("Second Name");

    private Button ok = new Button("OK");

    private boolean accepted = false;

    public boolean isAccepted() {
        return accepted;
    }

    public Button getOk() {
        return ok;
    }

    HumanEditDialog(String caption) {

        super(caption);
        setModal(true);
        setResizable(false);

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

        TextField specField = new TextField("Specialization");

        subContent.addComponent(specField,3);
        subContent.setComponentAlignment(specField, Alignment.TOP_CENTER);

        ok.addClickListener(clickEvent -> {



            accepted = true;
            close();
        });
    }

    void asEditDoctor(Doctor entity){

        asNewDoctor();

        lNameField.setValue(entity.getLastName());
        fNameField.setValue(entity.getFirstName());
        dNameField.setValue(entity.getDadsName());

        TextField spec = (TextField) subContent.getComponent(3);
        spec.setValue(entity.getSpeciality());

        ok.addClickListener(clickEvent -> {



            accepted = true;
            close();
        });
    }

    public void asNewPatient(){

        TextField phoneField = new TextField("Phone number");

        subContent.addComponent(phoneField, 3);
        subContent.setComponentAlignment(phoneField, Alignment.TOP_CENTER);

        ok.addClickListener(clickEvent -> {



            accepted = true;
            close();
        });
    }

    public void asEditPatient(Patient entity){

        asNewPatient();

        lNameField.setValue(entity.getLastName());
        fNameField.setValue(entity.getFirstName());
        dNameField.setValue(entity.getDadsName());

        TextField phone = (TextField) subContent.getComponent(3);
        phone.setValue(entity.getPhone());

        ok.addClickListener(clickEvent -> {



            accepted = true;
            close();
        });
    }
}
