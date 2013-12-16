package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.GssAtRule;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

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
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(13) : 0;
        if (this.getGssAtRule() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<GssAtRule> gssAtRule = this.getGssAtRule();
            size += ZippyBuffer.sizeOfRawVarInt(gssAtRule.size());
            for (GssAtRule entry : gssAtRule) {
                if (entry != null) {
                    size += entry.sizeOfGssAtRule(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
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
