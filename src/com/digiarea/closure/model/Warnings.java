package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.Warning;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class Warnings extends Node {

    private ListProperty<Warning> warning = new SimpleListProperty<Warning>(this, "warning", FXCollections.observableList(new ArrayList<Warning>()));

    public boolean addWarning(Warning warning) {
        boolean res = getWarning().add(warning);
        if (res) {
            warning.parent = this;
        }
        return res;
    }

    public boolean removeWarning(Warning warning) {
        boolean res = getWarning().remove(warning);
        if (res) {
            warning.parent = this;
        }
        return res;
    }

    public final List<Warning> getWarning() {
        return warning.get();
    }

    public final void setWarning(List<Warning> warning) {
        for (Warning item : warning) {
            item.parent = this;
        }
        this.warning.addAll(warning);
    }

    public Warnings() {
        super();
    }

    public Warnings(List<Warning> warning) {
        super();
        this.warning.addAll(warning);
    }

    public ListProperty<Warning> warningProperty() {
        return warning;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfWarnings(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(62) : 0;
        if (this.getWarning() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<Warning> warning = this.getWarning();
            size += ZippyBuffer.sizeOfRawVarInt(warning.size());
            for (Warning entry : warning) {
                if (entry != null) {
                    size += entry.sizeOfWarning(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeWarnings(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(62);
        }
        if (this.getWarning() != null) {
            writer.writeRawVarInt(1);
            final List<Warning> warning = this.getWarning();
            writer.writeRawVarInt(warning.size());
            for (Warning entry : warning) {
                if (entry != null) {
                    entry.writeWarning(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static Warnings readWarnings(final ZippyBuffer reader) throws IOException {
        final Warnings packet = new Warnings();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int warningI = reader.readRawVarInt();
                    final List<Warning> warning = new ArrayList<Warning>(warningI);
                    for (int i = 0; i < warningI; i++) {
                        warning.add(Warning.readWarning(reader));
                    }
                    packet.setWarning(warning);
                    break;
            }
        }
        return packet;
    }

}
