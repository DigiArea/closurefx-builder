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

public class JsDefines extends Node {

    private ListProperty<JsDefine> jsDefine = new SimpleListProperty<JsDefine>(this, "jsDefine", FXCollections.observableList(new ArrayList<JsDefine>()));

    public boolean addJsDefine(JsDefine jsDefine) {
        boolean res = getJsDefine().add(jsDefine);
        if (res) {
            if (jsDefine != null) {
                jsDefine.parent = this;
            }
        }
        return res;
    }

    public boolean removeJsDefine(JsDefine jsDefine) {
        boolean res = getJsDefine().remove(jsDefine);
        if (res) {
            if (jsDefine != null) {
                jsDefine.parent = this;
            }
        }
        return res;
    }

    public final List<JsDefine> getJsDefine() {
        return jsDefine.get();
    }

    public final void setJsDefine(List<JsDefine> jsDefine) {
        if (jsDefine != null) {
            for (JsDefine item : jsDefine) {
                item.parent = this;
            }
        }
        this.jsDefine.addAll(jsDefine);
    }

    public JsDefines() {
        super();
    }

    public JsDefines(List<JsDefine> jsDefine) {
        super();
        this.jsDefine.addAll(jsDefine);
    }

    public ListProperty<JsDefine> jsDefineProperty() {
        return jsDefine;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfJsDefines(final boolean isExternal) {
        int size = isExternal ? 1 : 0;
        if (this.getJsDefine() != null) {
            size += 1;
            final List<JsDefine> jsDefine = this.getJsDefine();
            size += ZippyBuffer.sizeOfRawVarInt(jsDefine.size());
            for (JsDefine entry : jsDefine) {
                if (entry != null) {
                    size += entry.sizeOfJsDefine(false);
                } else {
                    size += 1;
                }
            }
        }
        size += 1;
        return size;
    }

    public final void writeJsDefines(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(34);
        }
        if (this.getJsDefine() != null) {
            writer.writeRawVarInt(1);
            final List<JsDefine> jsDefine = this.getJsDefine();
            writer.writeRawVarInt(jsDefine.size());
            for (JsDefine entry : jsDefine) {
                if (entry != null) {
                    entry.writeJsDefine(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static JsDefines readJsDefines(final ZippyBuffer reader) throws IOException {
        final JsDefines packet = new JsDefines();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int jsDefineI = reader.readRawVarInt();
                    final List<JsDefine> jsDefine = new ArrayList<JsDefine>(jsDefineI);
                    for (int i = 0; i < jsDefineI; i++) {
                        jsDefine.add(JsDefine.readJsDefine(reader));
                    }
                    packet.setJsDefine(jsDefine);
                    break;
            }
        }
        return packet;
    }

}
