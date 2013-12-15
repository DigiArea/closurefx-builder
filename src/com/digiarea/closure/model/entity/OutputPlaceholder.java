package com.digiarea.closure.model.entity;


public enum OutputPlaceholder implements IPlaceholder {

    INPUT_DIRECTORY("{INPUT_DIRECTORY}", "Input Directory - parent directory of the current input file."), 
    INPUT_FILE_NAME("{INPUT_FILE_NAME}", "Input File Name - file name of the current input file."), 
    INPUT_FILE_NAME_NO_EXT("{INPUT_FILE_NAME_NO_EXT}", "Input File Name without Extension - file name without extension of the current input file."), 
    LOCALE("{LOCALE}", "Locale - current locale (pt-BR etc.)"), 
    LOCALE_LOWER_CASE("{LOCALE_LOWER_CASE}", "Locale - current locale in lower case(pt_br etc.)");

    private String value;

    private String description;

    private OutputPlaceholder(String value, String description) {
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
