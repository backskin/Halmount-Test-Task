package com.haulmont.view;

import com.haulmont.controller.DataService;
import com.haulmont.model.entities.Doctor;
import com.vaadin.ui.*;

import java.util.List;

class StatsDialog extends Window {

    StatsDialog(){
        super("Doctors Statistics");
        setModal(true);
        setResizable(false);
        setClosable(false);

        VerticalLayout verticalLayout = new VerticalLayout();

        List<Doctor> list = DataService.getDoctors();

        for (Doctor doctor : list) {

            HorizontalLayout layout = new HorizontalLayout();

            Label name = new Label(doctor.getFullName());
            Label amount = new Label("receipts: " + DataService.getDoctorReceipts(doctor).size());
            layout.addComponents(name,amount);
            layout.setComponentAlignment(name, Alignment.MIDDLE_LEFT);
            layout.setComponentAlignment(amount,Alignment.MIDDLE_RIGHT);
            layout.setSizeFull();
            verticalLayout.addComponent(layout);
        }

        Button ok = new Button("OK");
        ok.addClickListener(clickEvent1 -> close());

        verticalLayout.addComponent(ok);
        verticalLayout.setComponentAlignment(ok,Alignment.BOTTOM_CENTER);

        setContent(verticalLayout);
    }
}
