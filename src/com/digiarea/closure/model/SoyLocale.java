package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class SoyLocale extends Node {

    private StringProperty value = new SimpleStringProperty(this, "value");

    public final String getValue() {
        return value.get();
    }

    public final void setValue(String value) {
        this.value.set(value);
    }

    public SoyLocale() {
        super();
    }

    public SoyLocale(String value) {
        super();
        this.value.set(value);
    }

    public StringProperty valueProperty() {
        return value;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfSoyLocale(final boolean isExternal) {
        int size = isExternal ? 1 : 0;
        if (this.getValue() != null) {
            size += 1;
            size += ZippyBuffer.sizeOfString(this.getValue());
        }
        size += 1;
        return size;
    }

    public final void writeSoyLocale(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(57);
        }
        if (this.getValue() != null) {
            writer.writeRawVarInt(1);
            writer.writeString(this.getValue());
        }
        writer.writeRawVarInt(0);
    }

    public static SoyLocale readSoyLocale(final ZippyBuffer reader) throws IOException {
        final SoyLocale packet = new SoyLocale();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setValue(reader.readString());
                    break;
            }
        }
        return packet;
    }

}
