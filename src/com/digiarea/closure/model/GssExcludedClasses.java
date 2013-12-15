package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.GssExcludedClass;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class GssExcludedClasses extends Node {

    private ListProperty<GssExcludedClass> gssExcludedClass = new SimpleListProperty<GssExcludedClass>(this, "gssExcludedClass", FXCollections.observableList(new ArrayList<GssExcludedClass>()));

    public boolean addGssExcludedClass(GssExcludedClass gssExcludedClass) {
        boolean res = getGssExcludedClass().add(gssExcludedClass);
        if (res) {
            gssExcludedClass.parent = this;
        }
        return res;
    }

    public boolean removeGssExcludedClass(GssExcludedClass gssExcludedClass) {
        boolean res = getGssExcludedClass().remove(gssExcludedClass);
        if (res) {
            gssExcludedClass.parent = this;
        }
        return res;
    }

    public final List<GssExcludedClass> getGssExcludedClass() {
        return gssExcludedClass.get();
    }

    public final void setGssExcludedClass(List<GssExcludedClass> gssExcludedClass) {
        for (GssExcludedClass item : gssExcludedClass) {
            item.parent = this;
        }
        this.gssExcludedClass.addAll(gssExcludedClass);
    }

    public GssExcludedClasses() {
        super();
    }

    public GssExcludedClasses(List<GssExcludedClass> gssExcludedClass) {
        super();
        this.gssExcludedClass.addAll(gssExcludedClass);
    }

    public ListProperty<GssExcludedClass> gssExcludedClassProperty() {
        return gssExcludedClass;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfGssExcludedClasses(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(17) : 0;
        if (this.getGssExcludedClass() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<GssExcludedClass> gssExcludedClass = this.getGssExcludedClass();
            size += ZippyBuffer.sizeOfRawVarInt(gssExcludedClass.size());
            for (GssExcludedClass entry : gssExcludedClass) {
                if (entry != null) {
                    size += entry.sizeOfGssExcludedClass(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeGssExcludedClasses(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(17);
        }
        if (this.getGssExcludedClass() != null) {
            writer.writeRawVarInt(1);
            final List<GssExcludedClass> gssExcludedClass = this.getGssExcludedClass();
            writer.writeRawVarInt(gssExcludedClass.size());
            for (GssExcludedClass entry : gssExcludedClass) {
                if (entry != null) {
                    entry.writeGssExcludedClass(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static GssExcludedClasses readGssExcludedClasses(final ZippyBuffer reader) throws IOException {
        final GssExcludedClasses packet = new GssExcludedClasses();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int gssExcludedClassI = reader.readRawVarInt();
                    final List<GssExcludedClass> gssExcludedClass = new ArrayList<GssExcludedClass>(gssExcludedClassI);
                    for (int i = 0; i < gssExcludedClassI; i++) {
                        gssExcludedClass.add(GssExcludedClass.readGssExcludedClass(reader));
                    }
                    packet.setGssExcludedClass(gssExcludedClass);
                    break;
            }
        }
        return packet;
    }

}
