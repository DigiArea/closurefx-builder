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

public class Optimizations extends Node {

    private ListProperty<Optimization> optimization = new SimpleListProperty<Optimization>(this, "optimization", FXCollections.observableList(new ArrayList<Optimization>()));

    public boolean addOptimization(Optimization optimization) {
        boolean res = getOptimization().add(optimization);
        if (res) {
            if (optimization != null) {
                optimization.parent = this;
            }
        }
        return res;
    }

    public boolean removeOptimization(Optimization optimization) {
        boolean res = getOptimization().remove(optimization);
        if (res) {
            if (optimization != null) {
                optimization.parent = this;
            }
        }
        return res;
    }

    public final List<Optimization> getOptimization() {
        return optimization.get();
    }

    public final void setOptimization(List<Optimization> optimization) {
        if (optimization != null) {
            for (Optimization item : optimization) {
                item.parent = this;
            }
        }
        this.optimization.addAll(optimization);
    }

    public Optimizations() {
        super();
    }

    public Optimizations(List<Optimization> optimization) {
        super();
        this.optimization.addAll(optimization);
    }

    public ListProperty<Optimization> optimizationProperty() {
        return optimization;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfOptimizations(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(48) : 0;
        if (this.getOptimization() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<Optimization> optimization = this.getOptimization();
            size += ZippyBuffer.sizeOfRawVarInt(optimization.size());
            for (Optimization entry : optimization) {
                if (entry != null) {
                    size += entry.sizeOfOptimization(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeOptimizations(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(48);
        }
        if (this.getOptimization() != null) {
            writer.writeRawVarInt(1);
            final List<Optimization> optimization = this.getOptimization();
            writer.writeRawVarInt(optimization.size());
            for (Optimization entry : optimization) {
                if (entry != null) {
                    entry.writeOptimization(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static Optimizations readOptimizations(final ZippyBuffer reader) throws IOException {
        final Optimizations packet = new Optimizations();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int optimizationI = reader.readRawVarInt();
                    final List<Optimization> optimization = new ArrayList<Optimization>(optimizationI);
                    for (int i = 0; i < optimizationI; i++) {
                        optimization.add(Optimization.readOptimization(reader));
                    }
                    packet.setOptimization(optimization);
                    break;
            }
        }
        return packet;
    }

}
