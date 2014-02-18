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

public class Buildpath extends Node {

    private ListProperty<Source> source = new SimpleListProperty<Source>(this, "source", FXCollections.observableList(new ArrayList<Source>()));

    public boolean addSource(Source source) {
        boolean res = getSource().add(source);
        if (res) {
            if (source != null) {
                source.parent = this;
            }
        }
        return res;
    }

    public boolean removeSource(Source source) {
        boolean res = getSource().remove(source);
        if (res) {
            if (source != null) {
                source.parent = this;
            }
        }
        return res;
    }

    public final List<Source> getSource() {
        return source.get();
    }

    public final void setSource(List<Source> source) {
        if (source != null) {
            for (Source item : source) {
                item.parent = this;
            }
        }
        this.source.addAll(source);
    }

    public Buildpath() {
        super();
    }

    public Buildpath(List<Source> source) {
        super();
        this.source.addAll(source);
    }

    public ListProperty<Source> sourceProperty() {
        return source;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfBuildpath(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(1) : 0;
        if (this.getSource() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<Source> source = this.getSource();
            size += ZippyBuffer.sizeOfRawVarInt(source.size());
            for (Source entry : source) {
                if (entry != null) {
                    size += entry.sizeOfSource(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeBuildpath(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(1);
        }
        if (this.getSource() != null) {
            writer.writeRawVarInt(1);
            final List<Source> source = this.getSource();
            writer.writeRawVarInt(source.size());
            for (Source entry : source) {
                if (entry != null) {
                    entry.writeSource(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static Buildpath readBuildpath(final ZippyBuffer reader) throws IOException {
        final Buildpath packet = new Buildpath();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int sourceI = reader.readRawVarInt();
                    final List<Source> source = new ArrayList<Source>(sourceI);
                    for (int i = 0; i < sourceI; i++) {
                        source.add(Source.readSource(reader));
                    }
                    packet.setSource(source);
                    break;
            }
        }
        return packet;
    }

}
