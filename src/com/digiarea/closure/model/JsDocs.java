package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.JsDoc;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class JsDocs extends Node {

    private ListProperty<JsDoc> jsDoc = new SimpleListProperty<JsDoc>(this, "jsDoc", FXCollections.observableList(new ArrayList<JsDoc>()));

    public boolean addJsDoc(JsDoc jsDoc) {
        boolean res = getJsDoc().add(jsDoc);
        if (res) {
            if (jsDoc != null) {
                jsDoc.parent = this;
            }
        }
        return res;
    }

    public boolean removeJsDoc(JsDoc jsDoc) {
        boolean res = getJsDoc().remove(jsDoc);
        if (res) {
            if (jsDoc != null) {
                jsDoc.parent = this;
            }
        }
        return res;
    }

    public final List<JsDoc> getJsDoc() {
        return jsDoc.get();
    }

    public final void setJsDoc(List<JsDoc> jsDoc) {
        if (jsDoc != null) {
            for (JsDoc item : jsDoc) {
                item.parent = this;
            }
        }
        this.jsDoc.addAll(jsDoc);
    }

    public JsDocs() {
        super();
    }

    public JsDocs(List<JsDoc> jsDoc) {
        super();
        this.jsDoc.addAll(jsDoc);
    }

    public ListProperty<JsDoc> jsDocProperty() {
        return jsDoc;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfJsDocs(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(37) : 0;
        if (this.getJsDoc() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<JsDoc> jsDoc = this.getJsDoc();
            size += ZippyBuffer.sizeOfRawVarInt(jsDoc.size());
            for (JsDoc entry : jsDoc) {
                if (entry != null) {
                    size += entry.sizeOfJsDoc(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeJsDocs(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(37);
        }
        if (this.getJsDoc() != null) {
            writer.writeRawVarInt(1);
            final List<JsDoc> jsDoc = this.getJsDoc();
            writer.writeRawVarInt(jsDoc.size());
            for (JsDoc entry : jsDoc) {
                if (entry != null) {
                    entry.writeJsDoc(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static JsDocs readJsDocs(final ZippyBuffer reader) throws IOException {
        final JsDocs packet = new JsDocs();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int jsDocI = reader.readRawVarInt();
                    final List<JsDoc> jsDoc = new ArrayList<JsDoc>(jsDocI);
                    for (int i = 0; i < jsDocI; i++) {
                        jsDoc.add(JsDoc.readJsDoc(reader));
                    }
                    packet.setJsDoc(jsDoc);
                    break;
            }
        }
        return packet;
    }

}
