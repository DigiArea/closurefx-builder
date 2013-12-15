package com.digiarea.closure.model;

import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public enum GssOptimizationLevel {

    NONE, 
    SAVE, 
    MAXIMUM;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfGssOptimizationLevel() {
        int size = 0;
        size += ZippyBuffer.sizeOfRawVarInt(ordinal());
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeGssOptimizationLevel(final ZippyBuffer writer) throws IOException {
        writer.writeRawVarInt(ordinal());
        writer.writeRawVarInt(0);
    }

    public static GssOptimizationLevel readGssOptimizationLevel(final ZippyBuffer reader) throws IOException {
        final GssOptimizationLevel packet = GssOptimizationLevel.values()[reader.readRawVarInt()];
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
            }
        }
        return packet;
    }

}
