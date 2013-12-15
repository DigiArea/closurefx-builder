package com.digiarea.closure.preferences.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import com.digiarea.closure.preferences.model.EditorType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;
import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.CloneVisitor;

public class Editor {

    public static String MAC_OPEN = "usr/bin/open";

    private StringProperty name = new SimpleStringProperty(this, "name");

    private StringProperty path = new SimpleStringProperty(this, "path");

    private StringProperty file = new SimpleStringProperty(this, "file");

    private StringProperty line = new SimpleStringProperty(this, "line");

    private StringProperty column = new SimpleStringProperty(this, "column");

    private BooleanProperty isDefault = new SimpleBooleanProperty(this, "isDefault");

    public ObjectProperty<EditorType> type = new SimpleObjectProperty<EditorType>(this, "type");

    @Override
    public boolean equals(Object arg0) {
        if (arg0 instanceof Editor) {
            Editor editor = (Editor) arg0;
            return editor.type.getValue().equals(type.getValue());
        }
        return false;
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public final String getPath() {
        return path.get();
    }

    public final void setPath(String path) {
        this.path.set(path);
    }

    public final String getFile() {
        return file.get();
    }

    public final void setFile(String file) {
        this.file.set(file);
    }

    public final String getLine() {
        return line.get();
    }

    public final void setLine(String line) {
        this.line.set(line);
    }

    public final String getColumn() {
        return column.get();
    }

    public final void setColumn(String column) {
        this.column.set(column);
    }

    public final boolean isDefault() {
        return isDefault.get();
    }

    public final void setDefault(boolean isDefault) {
        this.isDefault.set(isDefault);
    }

    public final EditorType getType() {
        return type.get();
    }

    public final void setType(EditorType type) {
        this.type.set(type);
    }

    public Editor() {
        super();
    }

    public Editor(String name, String path, String file, String line, String column, boolean isDefault, EditorType type) {
        super();
        this.name.set(name);
        this.path.set(path);
        this.file.set(file);
        this.line.set(line);
        this.column.set(column);
        this.isDefault.set(isDefault);
        this.type.set(type);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty pathProperty() {
        return path;
    }

    public StringProperty fileProperty() {
        return file;
    }

    public StringProperty lineProperty() {
        return line;
    }

    public StringProperty columnProperty() {
        return column;
    }

    public BooleanProperty isDefaultProperty() {
        return isDefault;
    }

    public ObjectProperty<EditorType> typeProperty() {
        return type;
    }

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    @Override
    public final Editor clone() throws CloneNotSupportedException {
        try {
            return (Editor) accept(new CloneVisitor(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
