package com.digiarea.closure.model;

import java.io.IOException;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public enum GssOutputRenamingMapFormat {

    CLOSURE_COMPILED, 
    CLOSURE_COMPILED_SPLIT_HYPHENS, 
    CLOSURE_UNCOMPILED, 
    JSON, 
    PROPERTIES, 
    JSCOMP_VARIABLE_MAP;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfGssOutputRenamingMapFormat() {
        int size = 0;
        size += ZippyBuffer.sizeOfRawVarInt(ordinal());
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeGssOutputRenamingMapFormat(final ZippyBuffer writer) throws IOException {
        writer.writeRawVarInt(ordinal());
        writer.writeRawVarInt(0);
    }

    public static GssOutputRenamingMapFormat readGssOutputRenamingMapFormat(final ZippyBuffer reader) throws IOException {
        final GssOutputRenamingMapFormat packet = GssOutputRenamingMapFormat.values()[reader.readRawVarInt()];
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
            }
        }
        return packet;
    }

}
