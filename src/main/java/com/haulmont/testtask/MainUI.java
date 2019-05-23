package com.haulmont.testtask;

import com.haulmont.testtask.model.Doctor;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)

public class MainUI extends UI {

    @Override

    protected void init(VaadinRequest request) {

        DataService dataService = new DataService();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        layout.addComponent(new Label("Main UI"));

        for (Doctor d:
             dataService.getDoctors()) {
            layout.addComponent(new Label(d.getSpeciality()));
        }

        layout.addComponent(new Label("Total Doctors: " + dataService.getDoctors().size()));

        setContent(layout);
    }
}