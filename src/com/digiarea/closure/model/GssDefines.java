package com.digiarea.closure.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class GssDefines extends Node {

    private ListProperty<GssDefine> gssDefine = new SimpleListProperty<GssDefine>(this, "gssDefine", FXCollections.observableList(new ArrayList<GssDefine>()));

    public boolean addGssDefine(GssDefine gssDefine) {
        boolean res = getGssDefine().add(gssDefine);
        if (res) {
            if (gssDefine != null) {
                gssDefine.parent = this;
            }
        }
        return res;
    }

    public boolean removeGssDefine(GssDefine gssDefine) {
        boolean res = getGssDefine().remove(gssDefine);
        if (res) {
            if (gssDefine != null) {
                gssDefine.parent = this;
            }
        }
        return res;
    }

    public final List<GssDefine> getGssDefine() {
        return gssDefine.get();
    }

    public final void setGssDefine(List<GssDefine> gssDefine) {
        if (gssDefine != null) {
            for (GssDefine item : gssDefine) {
                item.parent = this;
            }
        }
        this.gssDefine.addAll(gssDefine);
    }

    public GssDefines() {
        super();
    }

    public GssDefines(List<GssDefine> gssDefine) {
        super();
        this.gssDefine.addAll(gssDefine);
    }

    public ListProperty<GssDefine> gssDefineProperty() {
        return gssDefine;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfGssDefines(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(15) : 0;
        if (this.getGssDefine() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<GssDefine> gssDefine = this.getGssDefine();
            size += ZippyBuffer.sizeOfRawVarInt(gssDefine.size());
            for (GssDefine entry : gssDefine) {
                if (entry != null) {
                    size += entry.sizeOfGssDefine(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeGssDefines(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(15);
        }
        if (this.getGssDefine() != null) {
            writer.writeRawVarInt(1);
            final List<GssDefine> gssDefine = this.getGssDefine();
            writer.writeRawVarInt(gssDefine.size());
            for (GssDefine entry : gssDefine) {
                if (entry != null) {
                    entry.writeGssDefine(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static GssDefines readGssDefines(final ZippyBuffer reader) throws IOException {
        final GssDefines packet = new GssDefines();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int gssDefineI = reader.readRawVarInt();
                    final List<GssDefine> gssDefine = new ArrayList<GssDefine>(gssDefineI);
                    for (int i = 0; i < gssDefineI; i++) {
                        gssDefine.add(GssDefine.readGssDefine(reader));
                    }
                    packet.setGssDefine(gssDefine);
                    break;
            }
        }
        return packet;
    }

}
