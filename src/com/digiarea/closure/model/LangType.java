package com.digiarea.closure.model;

import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public enum LangType {

    ECMASCRIPT_3, 
    ECMASCRIPT_5, 
    ECMASCRIPT_5_STRICT;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfLangType() {
        int size = 0;
        size += ZippyBuffer.sizeOfRawVarInt(ordinal());
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeLangType(final ZippyBuffer writer) throws IOException {
        writer.writeRawVarInt(ordinal());
        writer.writeRawVarInt(0);
    }

    public static LangType readLangType(final ZippyBuffer reader) throws IOException {
        final LangType packet = LangType.values()[reader.readRawVarInt()];
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
            }
        }
        return packet;
    }

}
