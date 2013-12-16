package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.GssNonStandardFunction;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class GssNonStandardFunctions extends Node {

    private ListProperty<GssNonStandardFunction> gssNonStandardFunction = new SimpleListProperty<GssNonStandardFunction>(this, "gssNonStandardFunction", FXCollections.observableList(new ArrayList<GssNonStandardFunction>()));

    public boolean addGssNonStandardFunction(GssNonStandardFunction gssNonStandardFunction) {
        boolean res = getGssNonStandardFunction().add(gssNonStandardFunction);
        if (res) {
            if (gssNonStandardFunction != null) {
                gssNonStandardFunction.parent = this;
            }
        }
        return res;
    }

    public boolean removeGssNonStandardFunction(GssNonStandardFunction gssNonStandardFunction) {
        boolean res = getGssNonStandardFunction().remove(gssNonStandardFunction);
        if (res) {
            if (gssNonStandardFunction != null) {
                gssNonStandardFunction.parent = this;
            }
        }
        return res;
    }

    public final List<GssNonStandardFunction> getGssNonStandardFunction() {
        return gssNonStandardFunction.get();
    }

    public final void setGssNonStandardFunction(List<GssNonStandardFunction> gssNonStandardFunction) {
        if (gssNonStandardFunction != null) {
            for (GssNonStandardFunction item : gssNonStandardFunction) {
                item.parent = this;
            }
        }
        this.gssNonStandardFunction.addAll(gssNonStandardFunction);
    }

    public GssNonStandardFunctions() {
        super();
    }

    public GssNonStandardFunctions(List<GssNonStandardFunction> gssNonStandardFunction) {
        super();
        this.gssNonStandardFunction.addAll(gssNonStandardFunction);
    }

    public ListProperty<GssNonStandardFunction> gssNonStandardFunctionProperty() {
        return gssNonStandardFunction;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfGssNonStandardFunctions(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(20) : 0;
        if (this.getGssNonStandardFunction() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<GssNonStandardFunction> gssNonStandardFunction = this.getGssNonStandardFunction();
            size += ZippyBuffer.sizeOfRawVarInt(gssNonStandardFunction.size());
            for (GssNonStandardFunction entry : gssNonStandardFunction) {
                if (entry != null) {
                    size += entry.sizeOfGssNonStandardFunction(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeGssNonStandardFunctions(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(20);
        }
        if (this.getGssNonStandardFunction() != null) {
            writer.writeRawVarInt(1);
            final List<GssNonStandardFunction> gssNonStandardFunction = this.getGssNonStandardFunction();
            writer.writeRawVarInt(gssNonStandardFunction.size());
            for (GssNonStandardFunction entry : gssNonStandardFunction) {
                if (entry != null) {
                    entry.writeGssNonStandardFunction(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static GssNonStandardFunctions readGssNonStandardFunctions(final ZippyBuffer reader) throws IOException {
        final GssNonStandardFunctions packet = new GssNonStandardFunctions();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int gssNonStandardFunctionI = reader.readRawVarInt();
                    final List<GssNonStandardFunction> gssNonStandardFunction = new ArrayList<GssNonStandardFunction>(gssNonStandardFunctionI);
                    for (int i = 0; i < gssNonStandardFunctionI; i++) {
                        gssNonStandardFunction.add(GssNonStandardFunction.readGssNonStandardFunction(reader));
                    }
                    packet.setGssNonStandardFunction(gssNonStandardFunction);
                    break;
            }
        }
        return packet;
    }

}
