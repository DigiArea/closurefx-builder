package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class JsDefine extends Node {

    private StringProperty name = new SimpleStringProperty(this, "name");

    private StringProperty value = new SimpleStringProperty(this, "value");

    private ObjectProperty<JsDefineType> type = new SimpleObjectProperty<JsDefineType>(this, "type");

    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public final String getValue() {
        return value.get();
    }

    public final void setValue(String value) {
        this.value.set(value);
    }

    public final JsDefineType getType() {
        return type.get();
    }

    public final void setType(JsDefineType type) {
        this.type.set(type);
    }

    public JsDefine() {
        super();
    }

    public JsDefine(String name, String value, JsDefineType type) {
        super();
        this.name.set(name);
        this.value.set(value);
        this.type.set(type);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty valueProperty() {
        return value;
    }

    public ObjectProperty<JsDefineType> typeProperty() {
        return type;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfJsDefine(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(33) : 0;
        if (this.getName() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += ZippyBuffer.sizeOfString(this.getName());
        }
        if (this.getValue() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += ZippyBuffer.sizeOfString(this.getValue());
        }
        if (this.getType() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(3);
            size += this.getType().sizeOfJsDefineType();
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeJsDefine(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(33);
        }
        if (this.getName() != null) {
            writer.writeRawVarInt(1);
            writer.writeString(this.getName());
        }
        if (this.getValue() != null) {
            writer.writeRawVarInt(2);
            writer.writeString(this.getValue());
        }
        if (this.getType() != null) {
            writer.writeRawVarInt(3);
            this.getType().writeJsDefineType(writer);
        }
        writer.writeRawVarInt(0);
    }

    public static JsDefine readJsDefine(final ZippyBuffer reader) throws IOException {
        final JsDefine packet = new JsDefine();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setName(reader.readString());
                    break;
                case 2:
                    packet.setValue(reader.readString());
                    break;
                case 3:
                    packet.setType(JsDefineType.readJsDefineType(reader));
                    break;
            }
        }
        return packet;
    }

}
