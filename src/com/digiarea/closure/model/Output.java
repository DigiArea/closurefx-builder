package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.dagxp.zippy.ZippyBuffer;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;

public class Output extends Node {

    private StringProperty path = new SimpleStringProperty(this, "path");

    private StringProperty file = new SimpleStringProperty(this, "file");

    public final String getPath() {
        return path.get();
    }

    public final void setPath(String path) {
        this.path.set(path);
    }

    public final String getFile() {
        return file.get();
    }

    public final void setFile(String file) {
        this.file.set(file);
    }

    public Output() {
        super();
    }

    public Output(String path, String file) {
        super();
        this.path.set(path);
        this.file.set(file);
    }

    public StringProperty pathProperty() {
        return path;
    }

    public StringProperty fileProperty() {
        return file;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfOutput(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(50) : 0;
        if (this.getPath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += ZippyBuffer.sizeOfString(this.getPath());
        }
        if (this.getFile() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += ZippyBuffer.sizeOfString(this.getFile());
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeOutput(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(50);
        }
        if (this.getPath() != null) {
            writer.writeRawVarInt(1);
            writer.writeString(this.getPath());
        }
        if (this.getFile() != null) {
            writer.writeRawVarInt(2);
            writer.writeString(this.getFile());
        }
        writer.writeRawVarInt(0);
    }

    public static Output readOutput(final ZippyBuffer reader) throws IOException {
        final Output packet = new Output();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setPath(reader.readString());
                    break;
                case 2:
                    packet.setFile(reader.readString());
                    break;
            }
        }
        return packet;
    }

}
