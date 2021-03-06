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

public class GssAtRules extends Node {

    private ListProperty<GssAtRule> gssAtRule = new SimpleListProperty<GssAtRule>(this, "gssAtRule", FXCollections.observableList(new ArrayList<GssAtRule>()));

    public boolean addGssAtRule(GssAtRule gssAtRule) {
        boolean res = getGssAtRule().add(gssAtRule);
        if (res) {
            if (gssAtRule != null) {
                gssAtRule.parent = this;
            }
        }
        return res;
    }

    public boolean removeGssAtRule(GssAtRule gssAtRule) {
        boolean res = getGssAtRule().remove(gssAtRule);
        if (res) {
            if (gssAtRule != null) {
                gssAtRule.parent = this;
            }
        }
        return res;
    }

    public final List<GssAtRule> getGssAtRule() {
        return gssAtRule.get();
    }

    public final void setGssAtRule(List<GssAtRule> gssAtRule) {
        if (gssAtRule != null) {
            for (GssAtRule item : gssAtRule) {
                item.parent = this;
            }
        }
        this.gssAtRule.addAll(gssAtRule);
    }

    public GssAtRules() {
        super();
    }

    public GssAtRules(List<GssAtRule> gssAtRule) {
        super();
        this.gssAtRule.addAll(gssAtRule);
    }

    public ListProperty<GssAtRule> gssAtRuleProperty() {
        return gssAtRule;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfGssAtRules(final boolean isExternal) {
        int size = isExternal ? 1 : 0;
        if (this.getGssAtRule() != null) {
            size += 1;
            final List<GssAtRule> gssAtRule = this.getGssAtRule();
            size += ZippyBuffer.sizeOfRawVarInt(gssAtRule.size());
            for (GssAtRule entry : gssAtRule) {
                if (entry != null) {
                    size += entry.sizeOfGssAtRule(false);
                } else {
                    size += 1;
                }
            }
        }
        size += 1;
        return size;
    }

    public final void writeGssAtRules(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(13);
        }
        if (this.getGssAtRule() != null) {
            writer.writeRawVarInt(1);
            final List<GssAtRule> gssAtRule = this.getGssAtRule();
            writer.writeRawVarInt(gssAtRule.size());
            for (GssAtRule entry : gssAtRule) {
                if (entry != null) {
                    entry.writeGssAtRule(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static GssAtRules readGssAtRules(final ZippyBuffer reader) throws IOException {
        final GssAtRules packet = new GssAtRules();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int gssAtRuleI = reader.readRawVarInt();
                    final List<GssAtRule> gssAtRule = new ArrayList<GssAtRule>(gssAtRuleI);
                    for (int i = 0; i < gssAtRuleI; i++) {
                        gssAtRule.add(GssAtRule.readGssAtRule(reader));
                    }
                    packet.setGssAtRule(gssAtRule);
                    break;
            }
        }
        return packet;
    }

}
