package com.haulmont.view;

import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import com.vaadin.ui.*;

public class DeleteDialog extends Window {

    private boolean accepted = false;

    private Label warning = new Label("ARE YOU SURE?");
    private Button ok = new Button("OK");
    private Button cancel = new Button("CANCEL");
    private HorizontalLayout hLayout = new HorizontalLayout(ok,cancel);
    private VerticalLayout layout = new VerticalLayout(warning, hLayout);

    public Button getOk() {
        return ok;
    }

    public boolean isAccepted() {
        return accepted;
    }

    DeleteDialog(Doctor entity){
        super("Doctor removing");
        setModal(true);
        setContent(layout);
    }

    DeleteDialog(Patient entity){
        super("Patient removing");
        setModal(true);
        setContent(layout);
    }

    DeleteDialog(Receipt entity){
        super("Receipt removing");
        setModal(true);
        setContent(layout);
    }
}
