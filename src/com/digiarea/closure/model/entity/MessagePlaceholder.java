package com.digiarea.closure.model.entity;


public enum MessagePlaceholder implements IPlaceholder {

    LOCALE("{LOCALE}", "Locale - current locale (pt-BR etc.)"), 
    LOCALE_LOWER_CASE("{LOCALE_LOWER_CASE}", "Locale - current locale in lower case(pt_br etc.)");

    private String value;

    private String description;

    private MessagePlaceholder(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
