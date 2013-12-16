package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.ExcludeInputFilter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.digiarea.closure.model.IncludeInputFilter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class Source extends Node {

    private ObjectProperty<ExcludeInputFilter> excluded = new SimpleObjectProperty<ExcludeInputFilter>(this, "excluded");

    private ObjectProperty<IncludeInputFilter> included = new SimpleObjectProperty<IncludeInputFilter>(this, "included");

    private BooleanProperty extern = new SimpleBooleanProperty(this, "extern");

    private StringProperty path = new SimpleStringProperty(this, "path");

    private ObjectProperty<SourceEntry> entryKind = new SimpleObjectProperty<SourceEntry>(this, "entryKind");

    private ObjectProperty<SourceEntity> entityKind = new SimpleObjectProperty<SourceEntity>(this, "entityKind");

    private BooleanProperty includeClosure = new SimpleBooleanProperty(this, "includeClosure");

    private BooleanProperty includeSimple = new SimpleBooleanProperty(this, "includeSimple");

    public final ExcludeInputFilter getExcluded() {
        return excluded.get();
    }

    public final void setExcluded(ExcludeInputFilter excluded) {
        if (excluded != null) {
            excluded.parent = this;
        }
        this.excluded.set(excluded);
    }

    public final IncludeInputFilter getIncluded() {
        return included.get();
    }

    public final void setIncluded(IncludeInputFilter included) {
        if (included != null) {
            included.parent = this;
        }
        this.included.set(included);
    }

    public final boolean isExtern() {
        return extern.get();
    }

    public final void setExtern(boolean extern) {
        this.extern.set(extern);
    }

    public final String getPath() {
        return path.get();
    }

    public final void setPath(String path) {
        this.path.set(path);
    }

    public final SourceEntry getEntryKind() {
        return entryKind.get();
    }

    public final void setEntryKind(SourceEntry entryKind) {
        this.entryKind.set(entryKind);
    }

    public final SourceEntity getEntityKind() {
        return entityKind.get();
    }

    public final void setEntityKind(SourceEntity entityKind) {
        this.entityKind.set(entityKind);
    }

    public final boolean isIncludeClosure() {
        return includeClosure.get();
    }

    public final void setIncludeClosure(boolean includeClosure) {
        this.includeClosure.set(includeClosure);
    }

    public final boolean isIncludeSimple() {
        return includeSimple.get();
    }

    public final void setIncludeSimple(boolean includeSimple) {
        this.includeSimple.set(includeSimple);
    }

    public Source() {
        super();
    }

    public Source(ExcludeInputFilter excluded, IncludeInputFilter included, boolean extern, String path, SourceEntry entryKind, SourceEntity entityKind, boolean includeClosure, boolean includeSimple) {
        super();
        this.excluded.set(excluded);
        this.included.set(included);
        this.extern.set(extern);
        this.path.set(path);
        this.entryKind.set(entryKind);
        this.entityKind.set(entityKind);
        this.includeClosure.set(includeClosure);
        this.includeSimple.set(includeSimple);
    }

    public ObjectProperty<ExcludeInputFilter> excludedProperty() {
        return excluded;
    }

    public ObjectProperty<IncludeInputFilter> includedProperty() {
        return included;
    }

    public BooleanProperty externProperty() {
        return extern;
    }

    public StringProperty pathProperty() {
        return path;
    }

    public ObjectProperty<SourceEntry> entryKindProperty() {
        return entryKind;
    }

    public ObjectProperty<SourceEntity> entityKindProperty() {
        return entityKind;
    }

    public BooleanProperty includeClosureProperty() {
        return includeClosure;
    }

    public BooleanProperty includeSimpleProperty() {
        return includeSimple;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfSource(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(52) : 0;
        if (this.getExcluded() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += this.getExcluded().sizeOfExcludeInputFilter(false);
        }
        if (this.getIncluded() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getIncluded().sizeOfIncludeInputFilter(false);
        }
        size += ZippyBuffer.sizeOfRawVarInt(3);
        size += ZippyBuffer.sizeOfBoolean(this.isExtern());
        if (this.getPath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(4);
            size += ZippyBuffer.sizeOfString(this.getPath());
        }
        if (this.getEntryKind() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(5);
            size += this.getEntryKind().sizeOfSourceEntry();
        }
        if (this.getEntityKind() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(6);
            size += this.getEntityKind().sizeOfSourceEntity();
        }
        size += ZippyBuffer.sizeOfRawVarInt(7);
        size += ZippyBuffer.sizeOfBoolean(this.isIncludeClosure());
        size += ZippyBuffer.sizeOfRawVarInt(8);
        size += ZippyBuffer.sizeOfBoolean(this.isIncludeSimple());
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeSource(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(52);
        }
        if (this.getExcluded() != null) {
            writer.writeRawVarInt(1);
            this.getExcluded().writeExcludeInputFilter(writer, false);
        }
        if (this.getIncluded() != null) {
            writer.writeRawVarInt(2);
            this.getIncluded().writeIncludeInputFilter(writer, false);
        }
        writer.writeRawVarInt(3);
        writer.writeBoolean(this.isExtern());
        if (this.getPath() != null) {
            writer.writeRawVarInt(4);
            writer.writeString(this.getPath());
        }
        if (this.getEntryKind() != null) {
            writer.writeRawVarInt(5);
            this.getEntryKind().writeSourceEntry(writer);
        }
        if (this.getEntityKind() != null) {
            writer.writeRawVarInt(6);
            this.getEntityKind().writeSourceEntity(writer);
        }
        writer.writeRawVarInt(7);
        writer.writeBoolean(this.isIncludeClosure());
        writer.writeRawVarInt(8);
        writer.writeBoolean(this.isIncludeSimple());
        writer.writeRawVarInt(0);
    }

    public static Source readSource(final ZippyBuffer reader) throws IOException {
        final Source packet = new Source();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setExcluded(ExcludeInputFilter.readExcludeInputFilter(reader));
                    break;
                case 2:
                    packet.setIncluded(IncludeInputFilter.readIncludeInputFilter(reader));
                    break;
                case 3:
                    packet.setExtern(reader.readBoolean());
                    break;
                case 4:
                    packet.setPath(reader.readString());
                    break;
                case 5:
                    packet.setEntryKind(SourceEntry.readSourceEntry(reader));
                    break;
                case 6:
                    packet.setEntityKind(SourceEntity.readSourceEntity(reader));
                    break;
                case 7:
                    packet.setIncludeClosure(reader.readBoolean());
                    break;
                case 8:
                    packet.setIncludeSimple(reader.readBoolean());
                    break;
            }
        }
        return packet;
    }

}
