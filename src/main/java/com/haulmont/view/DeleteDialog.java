package com.haulmont.view;

import com.haulmont.controller.DataService;
import com.haulmont.model.entities.Doctor;
import com.haulmont.model.entities.Patient;
import com.haulmont.model.entities.Receipt;
import com.vaadin.ui.*;

class DeleteDialog extends Window {

    private boolean accepted = false;

    private Label warning = new Label();
    private Button ok = new Button("OK");
    private Button cancel = new Button("CANCEL");
    private HorizontalLayout hLayout = new HorizontalLayout(ok,cancel);
    private VerticalLayout layout = new VerticalLayout(warning, hLayout);

    Button getOk() {
        return ok;
    }

    boolean isAccepted() {
        return accepted;
    }

    private void init(Object entity){
        setModal(true);
        setClosable(false);
        setResizable(false);
        layout.setComponentAlignment(hLayout,Alignment.BOTTOM_RIGHT);
        warning.setValue("Are you sure to delete '" + entity.toString() + "' from database?");
        cancel.addClickListener(clickEvent -> close());
        setContent(layout);
    }

    DeleteDialog(Doctor entity){
        super("Doctor removing");
        init(entity);

        ok.addClickListener(clickEvent -> {
            try {

                DataService.deleteDoctor(entity);
                accepted = true;
                close();


            } catch (Exception e) {
                new ErrorMessage(e);
            }
        });
    }

    DeleteDialog(Patient entity){
        super("Patient removing");
        init(entity);

        ok.addClickListener(clickEvent -> {
            try {

                DataService.deletePatient(entity);
                accepted = true;
                close();

            } catch (Exception e) {
                new ErrorMessage(e);
            }
        });
    }

    DeleteDialog(Receipt entity){
        super("Receipt removing");
        init(entity);

        ok.addClickListener(clickEvent -> {
            try {

                DataService.deleteReceipt(entity);
                accepted = true;
                close();

            } catch (Exception e) {
                new ErrorMessage(e);
            }
        });
    }
}
