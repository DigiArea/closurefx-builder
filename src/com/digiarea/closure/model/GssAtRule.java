package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class GssAtRule extends Node {

    private StringProperty value = new SimpleStringProperty(this, "value");

    public final String getValue() {
        return value.get();
    }

    public final void setValue(String value) {
        this.value.set(value);
    }

    public GssAtRule() {
        super();
    }

    public GssAtRule(String value) {
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

    public final int sizeOfGssAtRule(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(12) : 0;
        if (this.getValue() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += ZippyBuffer.sizeOfString(this.getValue());
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeGssAtRule(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(12);
        }
        if (this.getValue() != null) {
            writer.writeRawVarInt(1);
            writer.writeString(this.getValue());
        }
        writer.writeRawVarInt(0);
    }

    public static GssAtRule readGssAtRule(final ZippyBuffer reader) throws IOException {
        final GssAtRule packet = new GssAtRule();
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
