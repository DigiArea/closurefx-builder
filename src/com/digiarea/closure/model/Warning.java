package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.dagxp.zippy.ZippyBuffer;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;

public class Warning extends Node {

    private ObjectProperty<SeverityType> severity = new SimpleObjectProperty<SeverityType>(this, "severity");

    private ObjectProperty<WarningType> type = new SimpleObjectProperty<WarningType>(this, "type");

    public final SeverityType getSeverity() {
        return severity.get();
    }

    public final void setSeverity(SeverityType severity) {
        this.severity.set(severity);
    }

    public final WarningType getType() {
        return type.get();
    }

    public final void setType(WarningType type) {
        this.type.set(type);
    }

    public Warning() {
        super();
    }

    public Warning(SeverityType severity, WarningType type) {
        super();
        this.severity.set(severity);
        this.type.set(type);
    }

    public ObjectProperty<SeverityType> severityProperty() {
        return severity;
    }

    public ObjectProperty<WarningType> typeProperty() {
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

    public final int sizeOfWarning(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(59) : 0;
        if (this.getSeverity() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += this.getSeverity().sizeOfSeverityType();
        }
        if (this.getType() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getType().sizeOfWarningType();
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeWarning(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(59);
        }
        if (this.getSeverity() != null) {
            writer.writeRawVarInt(1);
            this.getSeverity().writeSeverityType(writer);
        }
        if (this.getType() != null) {
            writer.writeRawVarInt(2);
            this.getType().writeWarningType(writer);
        }
        writer.writeRawVarInt(0);
    }

    public static Warning readWarning(final ZippyBuffer reader) throws IOException {
        final Warning packet = new Warning();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setSeverity(SeverityType.readSeverityType(reader));
                    break;
                case 2:
                    packet.setType(WarningType.readWarningType(reader));
                    break;
            }
        }
        return packet;
    }

}
