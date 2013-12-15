package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.Info;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.SoyLocales;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.SoyCodeStyle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class ClosureSoy extends Node {

    private ObjectProperty<Info> info = new SimpleObjectProperty<Info>(this, "info");

    private ObjectProperty<Buildpath> buildpath = new SimpleObjectProperty<Buildpath>(this, "buildpath");

    private ObjectProperty<SoyLocales> soyLocales = new SimpleObjectProperty<SoyLocales>(this, "soyLocales");

    private ObjectProperty<SoyCssSchemeType> cssScheme = new SimpleObjectProperty<SoyCssSchemeType>(this, "cssScheme");

    private ObjectProperty<SoyCodeStyle> codeStyle = new SimpleObjectProperty<SoyCodeStyle>(this, "codeStyle");

    private BooleanProperty build = new SimpleBooleanProperty(this, "build");

    private BooleanProperty allowExternalCalls = new SimpleBooleanProperty(this, "allowExternalCalls");

    private BooleanProperty allowDeprecatedSyntax = new SimpleBooleanProperty(this, "allowDeprecatedSyntax");

    private BooleanProperty usingIjData = new SimpleBooleanProperty(this, "usingIjData");

    private BooleanProperty generateJsDoc = new SimpleBooleanProperty(this, "generateJsDoc");

    private BooleanProperty provideRequireSoyNamespaces = new SimpleBooleanProperty(this, "provideRequireSoyNamespaces");

    private BooleanProperty provideRequireJsFunctions = new SimpleBooleanProperty(this, "provideRequireJsFunctions");

    private BooleanProperty declareTopLevelNamespace = new SimpleBooleanProperty(this, "declareTopLevelNamespace");

    private BooleanProperty generateGoogMessagesDefs = new SimpleBooleanProperty(this, "generateGoogMessagesDefs");

    private BooleanProperty googMessagesExternal = new SimpleBooleanProperty(this, "googMessagesExternal");

    private BooleanProperty rightToLeftDir = new SimpleBooleanProperty(this, "rightToLeftDir");

    private BooleanProperty rightToLeftDirGoog = new SimpleBooleanProperty(this, "rightToLeftDirGoog");

    private StringProperty globalsPath = new SimpleStringProperty(this, "globalsPath");

    private StringProperty messagesPath = new SimpleStringProperty(this, "messagesPath");

    private StringProperty outputPath = new SimpleStringProperty(this, "outputPath");

    public final Info getInfo() {
        return info.get();
    }

    public final void setInfo(Info info) {
        info.parent = this;
        this.info.set(info);
    }

    public final Buildpath getBuildpath() {
        return buildpath.get();
    }

    public final void setBuildpath(Buildpath buildpath) {
        buildpath.parent = this;
        this.buildpath.set(buildpath);
    }

    public final SoyLocales getSoyLocales() {
        return soyLocales.get();
    }

    public final void setSoyLocales(SoyLocales soyLocales) {
        soyLocales.parent = this;
        this.soyLocales.set(soyLocales);
    }

    public final SoyCssSchemeType getCssScheme() {
        return cssScheme.get();
    }

    public final void setCssScheme(SoyCssSchemeType cssScheme) {
        this.cssScheme.set(cssScheme);
    }

    public final SoyCodeStyle getCodeStyle() {
        return codeStyle.get();
    }

    public final void setCodeStyle(SoyCodeStyle codeStyle) {
        this.codeStyle.set(codeStyle);
    }

    public final boolean isBuild() {
        return build.get();
    }

    public final void setBuild(boolean build) {
        this.build.set(build);
    }

    public final boolean isAllowExternalCalls() {
        return allowExternalCalls.get();
    }

    public final void setAllowExternalCalls(boolean allowExternalCalls) {
        this.allowExternalCalls.set(allowExternalCalls);
    }

    public final boolean isAllowDeprecatedSyntax() {
        return allowDeprecatedSyntax.get();
    }

    public final void setAllowDeprecatedSyntax(boolean allowDeprecatedSyntax) {
        this.allowDeprecatedSyntax.set(allowDeprecatedSyntax);
    }

    public final boolean isUsingIjData() {
        return usingIjData.get();
    }

    public final void setUsingIjData(boolean usingIjData) {
        this.usingIjData.set(usingIjData);
    }

    public final boolean isGenerateJsDoc() {
        return generateJsDoc.get();
    }

    public final void setGenerateJsDoc(boolean generateJsDoc) {
        this.generateJsDoc.set(generateJsDoc);
    }

    public final boolean isProvideRequireSoyNamespaces() {
        return provideRequireSoyNamespaces.get();
    }

    public final void setProvideRequireSoyNamespaces(boolean provideRequireSoyNamespaces) {
        this.provideRequireSoyNamespaces.set(provideRequireSoyNamespaces);
    }

    public final boolean isProvideRequireJsFunctions() {
        return provideRequireJsFunctions.get();
    }

    public final void setProvideRequireJsFunctions(boolean provideRequireJsFunctions) {
        this.provideRequireJsFunctions.set(provideRequireJsFunctions);
    }

    public final boolean isDeclareTopLevelNamespace() {
        return declareTopLevelNamespace.get();
    }

    public final void setDeclareTopLevelNamespace(boolean declareTopLevelNamespace) {
        this.declareTopLevelNamespace.set(declareTopLevelNamespace);
    }

    public final boolean isGenerateGoogMessagesDefs() {
        return generateGoogMessagesDefs.get();
    }

    public final void setGenerateGoogMessagesDefs(boolean generateGoogMessagesDefs) {
        this.generateGoogMessagesDefs.set(generateGoogMessagesDefs);
    }

    public final boolean isGoogMessagesExternal() {
        return googMessagesExternal.get();
    }

    public final void setGoogMessagesExternal(boolean googMessagesExternal) {
        this.googMessagesExternal.set(googMessagesExternal);
    }

    public final boolean isRightToLeftDir() {
        return rightToLeftDir.get();
    }

    public final void setRightToLeftDir(boolean rightToLeftDir) {
        this.rightToLeftDir.set(rightToLeftDir);
    }

    public final boolean isRightToLeftDirGoog() {
        return rightToLeftDirGoog.get();
    }

    public final void setRightToLeftDirGoog(boolean rightToLeftDirGoog) {
        this.rightToLeftDirGoog.set(rightToLeftDirGoog);
    }

    public final String getGlobalsPath() {
        return globalsPath.get();
    }

    public final void setGlobalsPath(String globalsPath) {
        this.globalsPath.set(globalsPath);
    }

    public final String getMessagesPath() {
        return messagesPath.get();
    }

    public final void setMessagesPath(String messagesPath) {
        this.messagesPath.set(messagesPath);
    }

    public final String getOutputPath() {
        return outputPath.get();
    }

    public final void setOutputPath(String outputPath) {
        this.outputPath.set(outputPath);
    }

    public ClosureSoy() {
        super();
    }

    public ClosureSoy(Info info, Buildpath buildpath, SoyLocales soyLocales, SoyCssSchemeType cssScheme, SoyCodeStyle codeStyle, boolean build, boolean allowExternalCalls, boolean allowDeprecatedSyntax, boolean usingIjData, boolean generateJsDoc, boolean provideRequireSoyNamespaces, boolean provideRequireJsFunctions, boolean declareTopLevelNamespace, boolean generateGoogMessagesDefs, boolean googMessagesExternal, boolean rightToLeftDir, boolean rightToLeftDirGoog, String globalsPath, String messagesPath, String outputPath) {
        super();
        this.info.set(info);
        this.buildpath.set(buildpath);
        this.soyLocales.set(soyLocales);
        this.cssScheme.set(cssScheme);
        this.codeStyle.set(codeStyle);
        this.build.set(build);
        this.allowExternalCalls.set(allowExternalCalls);
        this.allowDeprecatedSyntax.set(allowDeprecatedSyntax);
        this.usingIjData.set(usingIjData);
        this.generateJsDoc.set(generateJsDoc);
        this.provideRequireSoyNamespaces.set(provideRequireSoyNamespaces);
        this.provideRequireJsFunctions.set(provideRequireJsFunctions);
        this.declareTopLevelNamespace.set(declareTopLevelNamespace);
        this.generateGoogMessagesDefs.set(generateGoogMessagesDefs);
        this.googMessagesExternal.set(googMessagesExternal);
        this.rightToLeftDir.set(rightToLeftDir);
        this.rightToLeftDirGoog.set(rightToLeftDirGoog);
        this.globalsPath.set(globalsPath);
        this.messagesPath.set(messagesPath);
        this.outputPath.set(outputPath);
    }

    public ObjectProperty<Info> infoProperty() {
        return info;
    }

    public ObjectProperty<Buildpath> buildpathProperty() {
        return buildpath;
    }

    public ObjectProperty<SoyLocales> soyLocalesProperty() {
        return soyLocales;
    }

    public ObjectProperty<SoyCssSchemeType> cssSchemeProperty() {
        return cssScheme;
    }

    public ObjectProperty<SoyCodeStyle> codeStyleProperty() {
        return codeStyle;
    }

    public BooleanProperty buildProperty() {
        return build;
    }

    public BooleanProperty allowExternalCallsProperty() {
        return allowExternalCalls;
    }

    public BooleanProperty allowDeprecatedSyntaxProperty() {
        return allowDeprecatedSyntax;
    }

    public BooleanProperty usingIjDataProperty() {
        return usingIjData;
    }

    public BooleanProperty generateJsDocProperty() {
        return generateJsDoc;
    }

    public BooleanProperty provideRequireSoyNamespacesProperty() {
        return provideRequireSoyNamespaces;
    }

    public BooleanProperty provideRequireJsFunctionsProperty() {
        return provideRequireJsFunctions;
    }

    public BooleanProperty declareTopLevelNamespaceProperty() {
        return declareTopLevelNamespace;
    }

    public BooleanProperty generateGoogMessagesDefsProperty() {
        return generateGoogMessagesDefs;
    }

    public BooleanProperty googMessagesExternalProperty() {
        return googMessagesExternal;
    }

    public BooleanProperty rightToLeftDirProperty() {
        return rightToLeftDir;
    }

    public BooleanProperty rightToLeftDirGoogProperty() {
        return rightToLeftDirGoog;
    }

    public StringProperty globalsPathProperty() {
        return globalsPath;
    }

    public StringProperty messagesPathProperty() {
        return messagesPath;
    }

    public StringProperty outputPathProperty() {
        return outputPath;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfClosureSoy(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(8) : 0;
        if (this.getInfo() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += this.getInfo().sizeOfInfo(false);
        }
        if (this.getBuildpath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getBuildpath().sizeOfBuildpath(false);
        }
        if (this.getSoyLocales() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(3);
            size += this.getSoyLocales().sizeOfSoyLocales(false);
        }
        if (this.getCssScheme() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(4);
            size += this.getCssScheme().sizeOfSoyCssSchemeType();
        }
        if (this.getCodeStyle() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(5);
            size += this.getCodeStyle().sizeOfSoyCodeStyle();
        }
        size += ZippyBuffer.sizeOfRawVarInt(6);
        size += ZippyBuffer.sizeOfBoolean(this.isBuild());
        size += ZippyBuffer.sizeOfRawVarInt(7);
        size += ZippyBuffer.sizeOfBoolean(this.isAllowExternalCalls());
        size += ZippyBuffer.sizeOfRawVarInt(8);
        size += ZippyBuffer.sizeOfBoolean(this.isAllowDeprecatedSyntax());
        size += ZippyBuffer.sizeOfRawVarInt(9);
        size += ZippyBuffer.sizeOfBoolean(this.isUsingIjData());
        size += ZippyBuffer.sizeOfRawVarInt(10);
        size += ZippyBuffer.sizeOfBoolean(this.isGenerateJsDoc());
        size += ZippyBuffer.sizeOfRawVarInt(11);
        size += ZippyBuffer.sizeOfBoolean(this.isProvideRequireSoyNamespaces());
        size += ZippyBuffer.sizeOfRawVarInt(12);
        size += ZippyBuffer.sizeOfBoolean(this.isProvideRequireJsFunctions());
        size += ZippyBuffer.sizeOfRawVarInt(13);
        size += ZippyBuffer.sizeOfBoolean(this.isDeclareTopLevelNamespace());
        size += ZippyBuffer.sizeOfRawVarInt(14);
        size += ZippyBuffer.sizeOfBoolean(this.isGenerateGoogMessagesDefs());
        size += ZippyBuffer.sizeOfRawVarInt(15);
        size += ZippyBuffer.sizeOfBoolean(this.isGoogMessagesExternal());
        size += ZippyBuffer.sizeOfRawVarInt(16);
        size += ZippyBuffer.sizeOfBoolean(this.isRightToLeftDir());
        size += ZippyBuffer.sizeOfRawVarInt(17);
        size += ZippyBuffer.sizeOfBoolean(this.isRightToLeftDirGoog());
        if (this.getGlobalsPath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(18);
            size += ZippyBuffer.sizeOfString(this.getGlobalsPath());
        }
        if (this.getMessagesPath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(19);
            size += ZippyBuffer.sizeOfString(this.getMessagesPath());
        }
        if (this.getOutputPath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(20);
            size += ZippyBuffer.sizeOfString(this.getOutputPath());
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeClosureSoy(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(8);
        }
        if (this.getInfo() != null) {
            writer.writeRawVarInt(1);
            this.getInfo().writeInfo(writer, false);
        }
        if (this.getBuildpath() != null) {
            writer.writeRawVarInt(2);
            this.getBuildpath().writeBuildpath(writer, false);
        }
        if (this.getSoyLocales() != null) {
            writer.writeRawVarInt(3);
            this.getSoyLocales().writeSoyLocales(writer, false);
        }
        if (this.getCssScheme() != null) {
            writer.writeRawVarInt(4);
            this.getCssScheme().writeSoyCssSchemeType(writer);
        }
        if (this.getCodeStyle() != null) {
            writer.writeRawVarInt(5);
            this.getCodeStyle().writeSoyCodeStyle(writer);
        }
        writer.writeRawVarInt(6);
        writer.writeBoolean(this.isBuild());
        writer.writeRawVarInt(7);
        writer.writeBoolean(this.isAllowExternalCalls());
        writer.writeRawVarInt(8);
        writer.writeBoolean(this.isAllowDeprecatedSyntax());
        writer.writeRawVarInt(9);
        writer.writeBoolean(this.isUsingIjData());
        writer.writeRawVarInt(10);
        writer.writeBoolean(this.isGenerateJsDoc());
        writer.writeRawVarInt(11);
        writer.writeBoolean(this.isProvideRequireSoyNamespaces());
        writer.writeRawVarInt(12);
        writer.writeBoolean(this.isProvideRequireJsFunctions());
        writer.writeRawVarInt(13);
        writer.writeBoolean(this.isDeclareTopLevelNamespace());
        writer.writeRawVarInt(14);
        writer.writeBoolean(this.isGenerateGoogMessagesDefs());
        writer.writeRawVarInt(15);
        writer.writeBoolean(this.isGoogMessagesExternal());
        writer.writeRawVarInt(16);
        writer.writeBoolean(this.isRightToLeftDir());
        writer.writeRawVarInt(17);
        writer.writeBoolean(this.isRightToLeftDirGoog());
        if (this.getGlobalsPath() != null) {
            writer.writeRawVarInt(18);
            writer.writeString(this.getGlobalsPath());
        }
        if (this.getMessagesPath() != null) {
            writer.writeRawVarInt(19);
            writer.writeString(this.getMessagesPath());
        }
        if (this.getOutputPath() != null) {
            writer.writeRawVarInt(20);
            writer.writeString(this.getOutputPath());
        }
        writer.writeRawVarInt(0);
    }

    public static ClosureSoy readClosureSoy(final ZippyBuffer reader) throws IOException {
        final ClosureSoy packet = new ClosureSoy();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setInfo(Info.readInfo(reader));
                    break;
                case 2:
                    packet.setBuildpath(Buildpath.readBuildpath(reader));
                    break;
                case 3:
                    packet.setSoyLocales(SoyLocales.readSoyLocales(reader));
                    break;
                case 4:
                    packet.setCssScheme(SoyCssSchemeType.readSoyCssSchemeType(reader));
                    break;
                case 5:
                    packet.setCodeStyle(SoyCodeStyle.readSoyCodeStyle(reader));
                    break;
                case 6:
                    packet.setBuild(reader.readBoolean());
                    break;
                case 7:
                    packet.setAllowExternalCalls(reader.readBoolean());
                    break;
                case 8:
                    packet.setAllowDeprecatedSyntax(reader.readBoolean());
                    break;
                case 9:
                    packet.setUsingIjData(reader.readBoolean());
                    break;
                case 10:
                    packet.setGenerateJsDoc(reader.readBoolean());
                    break;
                case 11:
                    packet.setProvideRequireSoyNamespaces(reader.readBoolean());
                    break;
                case 12:
                    packet.setProvideRequireJsFunctions(reader.readBoolean());
                    break;
                case 13:
                    packet.setDeclareTopLevelNamespace(reader.readBoolean());
                    break;
                case 14:
                    packet.setGenerateGoogMessagesDefs(reader.readBoolean());
                    break;
                case 15:
                    packet.setGoogMessagesExternal(reader.readBoolean());
                    break;
                case 16:
                    packet.setRightToLeftDir(reader.readBoolean());
                    break;
                case 17:
                    packet.setRightToLeftDirGoog(reader.readBoolean());
                    break;
                case 18:
                    packet.setGlobalsPath(reader.readString());
                    break;
                case 19:
                    packet.setMessagesPath(reader.readString());
                    break;
                case 20:
                    packet.setOutputPath(reader.readString());
                    break;
            }
        }
        return packet;
    }

}
