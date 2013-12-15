package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.InputFilterPattern;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class ExcludeInputFilter extends Node {

    private ListProperty<InputFilterPattern> pattern = new SimpleListProperty<InputFilterPattern>(this, "pattern", FXCollections.observableList(new ArrayList<InputFilterPattern>()));

    public boolean addPattern(InputFilterPattern pattern) {
        boolean res = getPattern().add(pattern);
        if (res) {
            pattern.parent = this;
        }
        return res;
    }

    public boolean removePattern(InputFilterPattern pattern) {
        boolean res = getPattern().remove(pattern);
        if (res) {
            pattern.parent = this;
        }
        return res;
    }

    public final List<InputFilterPattern> getPattern() {
        return pattern.get();
    }

    public final void setPattern(List<InputFilterPattern> pattern) {
        for (InputFilterPattern item : pattern) {
            item.parent = this;
        }
        this.pattern.addAll(pattern);
    }

    public ExcludeInputFilter() {
        super();
    }

    public ExcludeInputFilter(List<InputFilterPattern> pattern) {
        super();
        this.pattern.addAll(pattern);
    }

    public ListProperty<InputFilterPattern> patternProperty() {
        return pattern;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfExcludeInputFilter(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(11) : 0;
        if (this.getPattern() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<InputFilterPattern> pattern = this.getPattern();
            size += ZippyBuffer.sizeOfRawVarInt(pattern.size());
            for (InputFilterPattern entry : pattern) {
                if (entry != null) {
                    size += entry.sizeOfInputFilterPattern(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeExcludeInputFilter(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(11);
        }
        if (this.getPattern() != null) {
            writer.writeRawVarInt(1);
            final List<InputFilterPattern> pattern = this.getPattern();
            writer.writeRawVarInt(pattern.size());
            for (InputFilterPattern entry : pattern) {
                if (entry != null) {
                    entry.writeInputFilterPattern(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static ExcludeInputFilter readExcludeInputFilter(final ZippyBuffer reader) throws IOException {
        final ExcludeInputFilter packet = new ExcludeInputFilter();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int patternI = reader.readRawVarInt();
                    final List<InputFilterPattern> pattern = new ArrayList<InputFilterPattern>(patternI);
                    for (int i = 0; i < patternI; i++) {
                        pattern.add(InputFilterPattern.readInputFilterPattern(reader));
                    }
                    packet.setPattern(pattern);
                    break;
            }
        }
        return packet;
    }

}
