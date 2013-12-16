package com.digiarea.closure.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import com.dagxp.zippy.ZippyBuffer;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;

public class GssUnrecognizeProperties extends Node {

    private ListProperty<GssUnrecognizeProperty> gssUnrecognizeProperty = new SimpleListProperty<GssUnrecognizeProperty>(this, "gssUnrecognizeProperty", FXCollections.observableList(new ArrayList<GssUnrecognizeProperty>()));

    public boolean addGssUnrecognizeProperty(GssUnrecognizeProperty gssUnrecognizeProperty) {
        boolean res = getGssUnrecognizeProperty().add(gssUnrecognizeProperty);
        if (res) {
            if (gssUnrecognizeProperty != null) {
                gssUnrecognizeProperty.parent = this;
            }
        }
        return res;
    }

    public boolean removeGssUnrecognizeProperty(GssUnrecognizeProperty gssUnrecognizeProperty) {
        boolean res = getGssUnrecognizeProperty().remove(gssUnrecognizeProperty);
        if (res) {
            if (gssUnrecognizeProperty != null) {
                gssUnrecognizeProperty.parent = this;
            }
        }
        return res;
    }

    public final List<GssUnrecognizeProperty> getGssUnrecognizeProperty() {
        return gssUnrecognizeProperty.get();
    }

    public final void setGssUnrecognizeProperty(List<GssUnrecognizeProperty> gssUnrecognizeProperty) {
        if (gssUnrecognizeProperty != null) {
            for (GssUnrecognizeProperty item : gssUnrecognizeProperty) {
                item.parent = this;
            }
        }
        this.gssUnrecognizeProperty.addAll(gssUnrecognizeProperty);
    }

    public GssUnrecognizeProperties() {
        super();
    }

    public GssUnrecognizeProperties(List<GssUnrecognizeProperty> gssUnrecognizeProperty) {
        super();
        this.gssUnrecognizeProperty.addAll(gssUnrecognizeProperty);
    }

    public ListProperty<GssUnrecognizeProperty> gssUnrecognizePropertyProperty() {
        return gssUnrecognizeProperty;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfGssUnrecognizeProperties(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(26) : 0;
        if (this.getGssUnrecognizeProperty() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<GssUnrecognizeProperty> gssUnrecognizeProperty = this.getGssUnrecognizeProperty();
            size += ZippyBuffer.sizeOfRawVarInt(gssUnrecognizeProperty.size());
            for (GssUnrecognizeProperty entry : gssUnrecognizeProperty) {
                if (entry != null) {
                    size += entry.sizeOfGssUnrecognizeProperty(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeGssUnrecognizeProperties(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(26);
        }
        if (this.getGssUnrecognizeProperty() != null) {
            writer.writeRawVarInt(1);
            final List<GssUnrecognizeProperty> gssUnrecognizeProperty = this.getGssUnrecognizeProperty();
            writer.writeRawVarInt(gssUnrecognizeProperty.size());
            for (GssUnrecognizeProperty entry : gssUnrecognizeProperty) {
                if (entry != null) {
                    entry.writeGssUnrecognizeProperty(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static GssUnrecognizeProperties readGssUnrecognizeProperties(final ZippyBuffer reader) throws IOException {
        final GssUnrecognizeProperties packet = new GssUnrecognizeProperties();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int gssUnrecognizePropertyI = reader.readRawVarInt();
                    final List<GssUnrecognizeProperty> gssUnrecognizeProperty = new ArrayList<GssUnrecognizeProperty>(gssUnrecognizePropertyI);
                    for (int i = 0; i < gssUnrecognizePropertyI; i++) {
                        gssUnrecognizeProperty.add(GssUnrecognizeProperty.readGssUnrecognizeProperty(reader));
                    }
                    packet.setGssUnrecognizeProperty(gssUnrecognizeProperty);
                    break;
            }
        }
        return packet;
    }

}
