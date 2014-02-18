package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class Closure extends Node {

    private ObjectProperty<ClosureJs> closureJs = new SimpleObjectProperty<ClosureJs>(this, "closureJs");

    private ObjectProperty<ClosureGss> closureGss = new SimpleObjectProperty<ClosureGss>(this, "closureGss");

    private ObjectProperty<ClosureSoy> closureSoy = new SimpleObjectProperty<ClosureSoy>(this, "closureSoy");

    public final ClosureJs getClosureJs() {
        return closureJs.get();
    }

    public final void setClosureJs(ClosureJs closureJs) {
        if (closureJs != null) {
            closureJs.parent = this;
        }
        this.closureJs.set(closureJs);
    }

    public final ClosureGss getClosureGss() {
        return closureGss.get();
    }

    public final void setClosureGss(ClosureGss closureGss) {
        if (closureGss != null) {
            closureGss.parent = this;
        }
        this.closureGss.set(closureGss);
    }

    public final ClosureSoy getClosureSoy() {
        return closureSoy.get();
    }

    public final void setClosureSoy(ClosureSoy closureSoy) {
        if (closureSoy != null) {
            closureSoy.parent = this;
        }
        this.closureSoy.set(closureSoy);
    }

    public Closure() {
        super();
    }

    public Closure(ClosureJs closureJs, ClosureGss closureGss, ClosureSoy closureSoy) {
        super();
        this.closureJs.set(closureJs);
        this.closureGss.set(closureGss);
        this.closureSoy.set(closureSoy);
    }

    public ObjectProperty<ClosureJs> closureJsProperty() {
        return closureJs;
    }

    public ObjectProperty<ClosureGss> closureGssProperty() {
        return closureGss;
    }

    public ObjectProperty<ClosureSoy> closureSoyProperty() {
        return closureSoy;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfClosure(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(5) : 0;
        if (this.getClosureJs() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += this.getClosureJs().sizeOfClosureJs(false);
        }
        if (this.getClosureGss() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getClosureGss().sizeOfClosureGss(false);
        }
        if (this.getClosureSoy() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(3);
            size += this.getClosureSoy().sizeOfClosureSoy(false);
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeClosure(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(5);
        }
        if (this.getClosureJs() != null) {
            writer.writeRawVarInt(1);
            this.getClosureJs().writeClosureJs(writer, false);
        }
        if (this.getClosureGss() != null) {
            writer.writeRawVarInt(2);
            this.getClosureGss().writeClosureGss(writer, false);
        }
        if (this.getClosureSoy() != null) {
            writer.writeRawVarInt(3);
            this.getClosureSoy().writeClosureSoy(writer, false);
        }
        writer.writeRawVarInt(0);
    }

    public static Closure readClosure(final ZippyBuffer reader) throws IOException {
        final Closure packet = new Closure();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setClosureJs(ClosureJs.readClosureJs(reader));
                    break;
                case 2:
                    packet.setClosureGss(ClosureGss.readClosureGss(reader));
                    break;
                case 3:
                    packet.setClosureSoy(ClosureSoy.readClosureSoy(reader));
                    break;
            }
        }
        return packet;
    }

}
