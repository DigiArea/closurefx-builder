package com.digiarea.closure.model.validation;

import javafx.scene.control.Control;

public class MessageCategory {

    private Control control;

    private String key;

    public MessageCategory(Control control, String key) {
        super();
        this.control = control;
        this.key = key;
    }

    public Control getControl() {
        return control;
    }

    public String getKey() {
        return key;
    }

}
