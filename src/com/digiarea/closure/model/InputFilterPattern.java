package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class InputFilterPattern extends Node {

    private StringProperty value = new SimpleStringProperty(this, "value");

    private ObjectProperty<InputFilterType> type = new SimpleObjectProperty<InputFilterType>(this, "type");

    public final String getValue() {
        return value.get();
    }

    public final void setValue(String value) {
        this.value.set(value);
    }

    public final InputFilterType getType() {
        return type.get();
    }

    public final void setType(InputFilterType type) {
        this.type.set(type);
    }

    public InputFilterPattern() {
        super();
    }

    public InputFilterPattern(String value, InputFilterType type) {
        super();
        this.value.set(value);
        this.type.set(type);
    }

    public StringProperty valueProperty() {
        return value;
    }

    public ObjectProperty<InputFilterType> typeProperty() {
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

    public final int sizeOfInputFilterPattern(final boolean isExternal) {
        int size = isExternal ? 1 : 0;
        if (this.getValue() != null) {
            size += 1;
            size += ZippyBuffer.sizeOfString(this.getValue());
        }
        if (this.getType() != null) {
            size += 1;
            size += this.getType().sizeOfInputFilterType();
        }
        size += 1;
        return size;
    }

    public final void writeInputFilterPattern(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(31);
        }
        if (this.getValue() != null) {
            writer.writeRawVarInt(1);
            writer.writeString(this.getValue());
        }
        if (this.getType() != null) {
            writer.writeRawVarInt(2);
            this.getType().writeInputFilterType(writer);
        }
        writer.writeRawVarInt(0);
    }

    public static InputFilterPattern readInputFilterPattern(final ZippyBuffer reader) throws IOException {
        final InputFilterPattern packet = new InputFilterPattern();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setValue(reader.readString());
                    break;
                case 2:
                    packet.setType(InputFilterType.readInputFilterType(reader));
                    break;
            }
        }
        return packet;
    }

}
