package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.dagxp.zippy.ZippyBuffer;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;

public class Language extends Node {

    private ObjectProperty<LangType> input = new SimpleObjectProperty<LangType>(this, "input");

    private ObjectProperty<LangType> output = new SimpleObjectProperty<LangType>(this, "output");

    public final LangType getInput() {
        return input.get();
    }

    public final void setInput(LangType input) {
        this.input.set(input);
    }

    public final LangType getOutput() {
        return output.get();
    }

    public final void setOutput(LangType output) {
        this.output.set(output);
    }

    public Language() {
        super();
    }

    public Language(LangType input, LangType output) {
        super();
        this.input.set(input);
        this.output.set(output);
    }

    public ObjectProperty<LangType> inputProperty() {
        return input;
    }

    public ObjectProperty<LangType> outputProperty() {
        return output;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfLanguage(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(45) : 0;
        if (this.getInput() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += this.getInput().sizeOfLangType();
        }
        if (this.getOutput() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getOutput().sizeOfLangType();
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeLanguage(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(45);
        }
        if (this.getInput() != null) {
            writer.writeRawVarInt(1);
            this.getInput().writeLangType(writer);
        }
        if (this.getOutput() != null) {
            writer.writeRawVarInt(2);
            this.getOutput().writeLangType(writer);
        }
        writer.writeRawVarInt(0);
    }

    public static Language readLanguage(final ZippyBuffer reader) throws IOException {
        final Language packet = new Language();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setInput(LangType.readLangType(reader));
                    break;
                case 2:
                    packet.setOutput(LangType.readLangType(reader));
                    break;
            }
        }
        return packet;
    }

}
