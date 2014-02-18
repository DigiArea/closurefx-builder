package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class Check extends Node {

    private BooleanProperty check = new SimpleBooleanProperty(this, "check");

    private ObjectProperty<CheckType> type = new SimpleObjectProperty<CheckType>(this, "type");

    public final boolean isCheck() {
        return check.get();
    }

    public final void setCheck(boolean check) {
        this.check.set(check);
    }

    public final CheckType getType() {
        return type.get();
    }

    public final void setType(CheckType type) {
        this.type.set(type);
    }

    public Check() {
        super();
    }

    public Check(boolean check, CheckType type) {
        super();
        this.check.set(check);
        this.type.set(type);
    }

    public BooleanProperty checkProperty() {
        return check;
    }

    public ObjectProperty<CheckType> typeProperty() {
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

    public final int sizeOfCheck(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(2) : 0;
        size += ZippyBuffer.sizeOfRawVarInt(1);
        size += ZippyBuffer.sizeOfBoolean(this.isCheck());
        if (this.getType() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getType().sizeOfCheckType();
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeCheck(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(2);
        }
        writer.writeRawVarInt(1);
        writer.writeBoolean(this.isCheck());
        if (this.getType() != null) {
            writer.writeRawVarInt(2);
            this.getType().writeCheckType(writer);
        }
        writer.writeRawVarInt(0);
    }

    public static Check readCheck(final ZippyBuffer reader) throws IOException {
        final Check packet = new Check();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setCheck(reader.readBoolean());
                    break;
                case 2:
                    packet.setType(CheckType.readCheckType(reader));
                    break;
            }
        }
        return packet;
    }

}
