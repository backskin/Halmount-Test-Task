package com.haulmont.view;

import com.haulmont.model.entities.Receipt;
import com.vaadin.ui.*;

public class ReceiptEditDialog extends Window {

    TextArea description = new TextArea("description");
    NativeSelect docSelect = new NativeSelect("Doctor: ");

    Button ok = new Button("OK");
    Button cancel = new Button("CANCEL");



    ReceiptEditDialog(){

        super("New Receipt");
        description.setPlaceholder("put receipt here...");

    }

    ReceiptEditDialog(Receipt receipt){
        super("Edit Receipt");
    }
}
