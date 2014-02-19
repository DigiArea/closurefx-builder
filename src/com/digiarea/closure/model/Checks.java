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

public class Checks extends Node {

    private ListProperty<Check> check = new SimpleListProperty<Check>(this, "check", FXCollections.observableList(new ArrayList<Check>()));

    public boolean addCheck(Check check) {
        boolean res = getCheck().add(check);
        if (res) {
            if (check != null) {
                check.parent = this;
            }
        }
        return res;
    }

    public boolean removeCheck(Check check) {
        boolean res = getCheck().remove(check);
        if (res) {
            if (check != null) {
                check.parent = this;
            }
        }
        return res;
    }

    public final List<Check> getCheck() {
        return check.get();
    }

    public final void setCheck(List<Check> check) {
        if (check != null) {
            for (Check item : check) {
                item.parent = this;
            }
        }
        this.check.addAll(check);
    }

    public Checks() {
        super();
    }

    public Checks(List<Check> check) {
        super();
        this.check.addAll(check);
    }

    public ListProperty<Check> checkProperty() {
        return check;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfChecks(final boolean isExternal) {
        int size = isExternal ? 1 : 0;
        if (this.getCheck() != null) {
            size += 1;
            final List<Check> check = this.getCheck();
            size += ZippyBuffer.sizeOfRawVarInt(check.size());
            for (Check entry : check) {
                if (entry != null) {
                    size += entry.sizeOfCheck(false);
                } else {
                    size += 1;
                }
            }
        }
        size += 1;
        return size;
    }

    public final void writeChecks(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(3);
        }
        if (this.getCheck() != null) {
            writer.writeRawVarInt(1);
            final List<Check> check = this.getCheck();
            writer.writeRawVarInt(check.size());
            for (Check entry : check) {
                if (entry != null) {
                    entry.writeCheck(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static Checks readChecks(final ZippyBuffer reader) throws IOException {
        final Checks packet = new Checks();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int checkI = reader.readRawVarInt();
                    final List<Check> check = new ArrayList<Check>(checkI);
                    for (int i = 0; i < checkI; i++) {
                        check.add(Check.readCheck(reader));
                    }
                    packet.setCheck(check);
                    break;
            }
        }
        return packet;
    }

}
