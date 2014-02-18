package com.digiarea.closure.model;

import java.io.IOException;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public enum JsRenamingVariablePolice {

    OFF, 
    LOCAL, 
    ALL;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfJsRenamingVariablePolice() {
        int size = 0;
        size += ZippyBuffer.sizeOfRawVarInt(ordinal());
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeJsRenamingVariablePolice(final ZippyBuffer writer) throws IOException {
        writer.writeRawVarInt(ordinal());
        writer.writeRawVarInt(0);
    }

    public static JsRenamingVariablePolice readJsRenamingVariablePolice(final ZippyBuffer reader) throws IOException {
        final JsRenamingVariablePolice packet = JsRenamingVariablePolice.values()[reader.readRawVarInt()];
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
            }
        }
        return packet;
    }

}
