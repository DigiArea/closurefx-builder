package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class Info extends Node {

    private StringProperty id = new SimpleStringProperty(this, "id");

    private StringProperty version = new SimpleStringProperty(this, "version");

    private StringProperty name = new SimpleStringProperty(this, "name");

    private StringProperty vendor = new SimpleStringProperty(this, "vendor");

    public final String getId() {
        return id.get();
    }

    public final void setId(String id) {
        this.id.set(id);
    }

    public final String getVersion() {
        return version.get();
    }

    public final void setVersion(String version) {
        this.version.set(version);
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public final String getVendor() {
        return vendor.get();
    }

    public final void setVendor(String vendor) {
        this.vendor.set(vendor);
    }

    public Info() {
        super();
    }

    public Info(String id, String version, String name, String vendor) {
        super();
        this.id.set(id);
        this.version.set(version);
        this.name.set(name);
        this.vendor.set(vendor);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty versionProperty() {
        return version;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty vendorProperty() {
        return vendor;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfInfo(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(30) : 0;
        if (this.getId() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += ZippyBuffer.sizeOfString(this.getId());
        }
        if (this.getVersion() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += ZippyBuffer.sizeOfString(this.getVersion());
        }
        if (this.getName() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(3);
            size += ZippyBuffer.sizeOfString(this.getName());
        }
        if (this.getVendor() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(4);
            size += ZippyBuffer.sizeOfString(this.getVendor());
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeInfo(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(30);
        }
        if (this.getId() != null) {
            writer.writeRawVarInt(1);
            writer.writeString(this.getId());
        }
        if (this.getVersion() != null) {
            writer.writeRawVarInt(2);
            writer.writeString(this.getVersion());
        }
        if (this.getName() != null) {
            writer.writeRawVarInt(3);
            writer.writeString(this.getName());
        }
        if (this.getVendor() != null) {
            writer.writeRawVarInt(4);
            writer.writeString(this.getVendor());
        }
        writer.writeRawVarInt(0);
    }

    public static Info readInfo(final ZippyBuffer reader) throws IOException {
        final Info packet = new Info();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setId(reader.readString());
                    break;
                case 2:
                    packet.setVersion(reader.readString());
                    break;
                case 3:
                    packet.setName(reader.readString());
                    break;
                case 4:
                    packet.setVendor(reader.readString());
                    break;
            }
        }
        return packet;
    }

}
