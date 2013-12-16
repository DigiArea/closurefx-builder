package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.SoyLocale;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class SoyLocales extends Node {

    private ListProperty<SoyLocale> soyLocale = new SimpleListProperty<SoyLocale>(this, "soyLocale", FXCollections.observableList(new ArrayList<SoyLocale>()));

    public boolean addSoyLocale(SoyLocale soyLocale) {
        boolean res = getSoyLocale().add(soyLocale);
        if (res) {
            if (soyLocale != null) {
                soyLocale.parent = this;
            }
        }
        return res;
    }

    public boolean removeSoyLocale(SoyLocale soyLocale) {
        boolean res = getSoyLocale().remove(soyLocale);
        if (res) {
            if (soyLocale != null) {
                soyLocale.parent = this;
            }
        }
        return res;
    }

    public final List<SoyLocale> getSoyLocale() {
        return soyLocale.get();
    }

    public final void setSoyLocale(List<SoyLocale> soyLocale) {
        if (soyLocale != null) {
            for (SoyLocale item : soyLocale) {
                item.parent = this;
            }
        }
        this.soyLocale.addAll(soyLocale);
    }

    public SoyLocales() {
        super();
    }

    public SoyLocales(List<SoyLocale> soyLocale) {
        super();
        this.soyLocale.addAll(soyLocale);
    }

    public ListProperty<SoyLocale> soyLocaleProperty() {
        return soyLocale;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfSoyLocales(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(58) : 0;
        if (this.getSoyLocale() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<SoyLocale> soyLocale = this.getSoyLocale();
            size += ZippyBuffer.sizeOfRawVarInt(soyLocale.size());
            for (SoyLocale entry : soyLocale) {
                if (entry != null) {
                    size += entry.sizeOfSoyLocale(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeSoyLocales(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(58);
        }
        if (this.getSoyLocale() != null) {
            writer.writeRawVarInt(1);
            final List<SoyLocale> soyLocale = this.getSoyLocale();
            writer.writeRawVarInt(soyLocale.size());
            for (SoyLocale entry : soyLocale) {
                if (entry != null) {
                    entry.writeSoyLocale(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static SoyLocales readSoyLocales(final ZippyBuffer reader) throws IOException {
        final SoyLocales packet = new SoyLocales();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int soyLocaleI = reader.readRawVarInt();
                    final List<SoyLocale> soyLocale = new ArrayList<SoyLocale>(soyLocaleI);
                    for (int i = 0; i < soyLocaleI; i++) {
                        soyLocale.add(SoyLocale.readSoyLocale(reader));
                    }
                    packet.setSoyLocale(soyLocale);
                    break;
            }
        }
        return packet;
    }

}
