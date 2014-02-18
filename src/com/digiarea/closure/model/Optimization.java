package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class Optimization extends Node {

    private BooleanProperty optimize = new SimpleBooleanProperty(this, "optimize");

    private ObjectProperty<OptimizationType> type = new SimpleObjectProperty<OptimizationType>(this, "type");

    public final boolean isOptimize() {
        return optimize.get();
    }

    public final void setOptimize(boolean optimize) {
        this.optimize.set(optimize);
    }

    public final OptimizationType getType() {
        return type.get();
    }

    public final void setType(OptimizationType type) {
        this.type.set(type);
    }

    public Optimization() {
        super();
    }

    public Optimization(boolean optimize, OptimizationType type) {
        super();
        this.optimize.set(optimize);
        this.type.set(type);
    }

    public BooleanProperty optimizeProperty() {
        return optimize;
    }

    public ObjectProperty<OptimizationType> typeProperty() {
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

    public final int sizeOfOptimization(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(47) : 0;
        size += ZippyBuffer.sizeOfRawVarInt(1);
        size += ZippyBuffer.sizeOfBoolean(this.isOptimize());
        if (this.getType() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getType().sizeOfOptimizationType();
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeOptimization(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(47);
        }
        writer.writeRawVarInt(1);
        writer.writeBoolean(this.isOptimize());
        if (this.getType() != null) {
            writer.writeRawVarInt(2);
            this.getType().writeOptimizationType(writer);
        }
        writer.writeRawVarInt(0);
    }

    public static Optimization readOptimization(final ZippyBuffer reader) throws IOException {
        final Optimization packet = new Optimization();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setOptimize(reader.readBoolean());
                    break;
                case 2:
                    packet.setType(OptimizationType.readOptimizationType(reader));
                    break;
            }
        }
        return packet;
    }

}
