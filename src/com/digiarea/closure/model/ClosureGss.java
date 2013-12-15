package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.Info;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Output;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import com.digiarea.closure.model.GssDefines;
import com.digiarea.closure.model.GssNonStandardFunctions;
import com.digiarea.closure.model.GssUnrecognizeProperties;
import com.digiarea.closure.model.GssAtRules;
import com.digiarea.closure.model.GssExcludedClasses;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import com.digiarea.closure.model.GssRenamingType;
import com.digiarea.closure.model.GssVendor;
import com.digiarea.closure.model.GssOutputRenamingMapFormat;
import com.digiarea.closure.model.GssOptimizationLevel;
import com.digiarea.closure.model.GssOutputFormat;
import com.digiarea.closure.model.GssInputOrientation;
import com.digiarea.closure.model.GssOutputOrientation;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class ClosureGss extends Node {

    private ObjectProperty<Info> info = new SimpleObjectProperty<Info>(this, "info");

    private ObjectProperty<Buildpath> buildpath = new SimpleObjectProperty<Buildpath>(this, "buildpath");

    private ObjectProperty<Output> output = new SimpleObjectProperty<Output>(this, "output");

    private StringProperty copyrightNotice = new SimpleStringProperty(this, "copyrightNotice");

    private ObjectProperty<GssDefines> gssDefines = new SimpleObjectProperty<GssDefines>(this, "gssDefines");

    private ObjectProperty<GssNonStandardFunctions> gssNonStandardFunctions = new SimpleObjectProperty<GssNonStandardFunctions>(this, "gssNonStandardFunctions");

    private ObjectProperty<GssUnrecognizeProperties> gssUnrecognizeProperties = new SimpleObjectProperty<GssUnrecognizeProperties>(this, "gssUnrecognizeProperties");

    private ObjectProperty<GssAtRules> gssAtRules = new SimpleObjectProperty<GssAtRules>(this, "gssAtRules");

    private ObjectProperty<GssExcludedClasses> gssExcludedClasses = new SimpleObjectProperty<GssExcludedClasses>(this, "gssExcludedClasses");

    private BooleanProperty build = new SimpleBooleanProperty(this, "build");

    private BooleanProperty allowUnrecognizedFunctions = new SimpleBooleanProperty(this, "allowUnrecognizedFunctions");

    private BooleanProperty allowUnrecognizedProperties = new SimpleBooleanProperty(this, "allowUnrecognizedProperties");

    private StringProperty cssRenamingPrefix = new SimpleStringProperty(this, "cssRenamingPrefix");

    private StringProperty outputRenamingMap = new SimpleStringProperty(this, "outputRenamingMap");

    private ObjectProperty<GssRenamingType> renamingType = new SimpleObjectProperty<GssRenamingType>(this, "renamingType");

    private ObjectProperty<GssVendor> vendor = new SimpleObjectProperty<GssVendor>(this, "vendor");

    private ObjectProperty<GssOutputRenamingMapFormat> outputRenamingMapFormat = new SimpleObjectProperty<GssOutputRenamingMapFormat>(this, "outputRenamingMapFormat");

    private ObjectProperty<GssOptimizationLevel> optimizationLevel = new SimpleObjectProperty<GssOptimizationLevel>(this, "optimizationLevel");

    private ObjectProperty<GssOutputFormat> outputFormat = new SimpleObjectProperty<GssOutputFormat>(this, "outputFormat");

    private ObjectProperty<GssInputOrientation> inputOrientation = new SimpleObjectProperty<GssInputOrientation>(this, "inputOrientation");

    private ObjectProperty<GssOutputOrientation> outputOrientation = new SimpleObjectProperty<GssOutputOrientation>(this, "outputOrientation");

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

    public final Output getOutput() {
        return output.get();
    }

    public final void setOutput(Output output) {
        output.parent = this;
        this.output.set(output);
    }

    public final String getCopyrightNotice() {
        return copyrightNotice.get();
    }

    public final void setCopyrightNotice(String copyrightNotice) {
        this.copyrightNotice.set(copyrightNotice);
    }

    public final GssDefines getGssDefines() {
        return gssDefines.get();
    }

    public final void setGssDefines(GssDefines gssDefines) {
        gssDefines.parent = this;
        this.gssDefines.set(gssDefines);
    }

    public final GssNonStandardFunctions getGssNonStandardFunctions() {
        return gssNonStandardFunctions.get();
    }

    public final void setGssNonStandardFunctions(GssNonStandardFunctions gssNonStandardFunctions) {
        gssNonStandardFunctions.parent = this;
        this.gssNonStandardFunctions.set(gssNonStandardFunctions);
    }

    public final GssUnrecognizeProperties getGssUnrecognizeProperties() {
        return gssUnrecognizeProperties.get();
    }

    public final void setGssUnrecognizeProperties(GssUnrecognizeProperties gssUnrecognizeProperties) {
        gssUnrecognizeProperties.parent = this;
        this.gssUnrecognizeProperties.set(gssUnrecognizeProperties);
    }

    public final GssAtRules getGssAtRules() {
        return gssAtRules.get();
    }

    public final void setGssAtRules(GssAtRules gssAtRules) {
        gssAtRules.parent = this;
        this.gssAtRules.set(gssAtRules);
    }

    public final GssExcludedClasses getGssExcludedClasses() {
        return gssExcludedClasses.get();
    }

    public final void setGssExcludedClasses(GssExcludedClasses gssExcludedClasses) {
        gssExcludedClasses.parent = this;
        this.gssExcludedClasses.set(gssExcludedClasses);
    }

    public final boolean isBuild() {
        return build.get();
    }

    public final void setBuild(boolean build) {
        this.build.set(build);
    }

    public final boolean isAllowUnrecognizedFunctions() {
        return allowUnrecognizedFunctions.get();
    }

    public final void setAllowUnrecognizedFunctions(boolean allowUnrecognizedFunctions) {
        this.allowUnrecognizedFunctions.set(allowUnrecognizedFunctions);
    }

    public final boolean isAllowUnrecognizedProperties() {
        return allowUnrecognizedProperties.get();
    }

    public final void setAllowUnrecognizedProperties(boolean allowUnrecognizedProperties) {
        this.allowUnrecognizedProperties.set(allowUnrecognizedProperties);
    }

    public final String getCssRenamingPrefix() {
        return cssRenamingPrefix.get();
    }

    public final void setCssRenamingPrefix(String cssRenamingPrefix) {
        this.cssRenamingPrefix.set(cssRenamingPrefix);
    }

    public final String getOutputRenamingMap() {
        return outputRenamingMap.get();
    }

    public final void setOutputRenamingMap(String outputRenamingMap) {
        this.outputRenamingMap.set(outputRenamingMap);
    }

    public final GssRenamingType getRenamingType() {
        return renamingType.get();
    }

    public final void setRenamingType(GssRenamingType renamingType) {
        this.renamingType.set(renamingType);
    }

    public final GssVendor getVendor() {
        return vendor.get();
    }

    public final void setVendor(GssVendor vendor) {
        this.vendor.set(vendor);
    }

    public final GssOutputRenamingMapFormat getOutputRenamingMapFormat() {
        return outputRenamingMapFormat.get();
    }

    public final void setOutputRenamingMapFormat(GssOutputRenamingMapFormat outputRenamingMapFormat) {
        this.outputRenamingMapFormat.set(outputRenamingMapFormat);
    }

    public final GssOptimizationLevel getOptimizationLevel() {
        return optimizationLevel.get();
    }

    public final void setOptimizationLevel(GssOptimizationLevel optimizationLevel) {
        this.optimizationLevel.set(optimizationLevel);
    }

    public final GssOutputFormat getOutputFormat() {
        return outputFormat.get();
    }

    public final void setOutputFormat(GssOutputFormat outputFormat) {
        this.outputFormat.set(outputFormat);
    }

    public final GssInputOrientation getInputOrientation() {
        return inputOrientation.get();
    }

    public final void setInputOrientation(GssInputOrientation inputOrientation) {
        this.inputOrientation.set(inputOrientation);
    }

    public final GssOutputOrientation getOutputOrientation() {
        return outputOrientation.get();
    }

    public final void setOutputOrientation(GssOutputOrientation outputOrientation) {
        this.outputOrientation.set(outputOrientation);
    }

    public ClosureGss() {
        super();
    }

    public ClosureGss(Info info, Buildpath buildpath, Output output, String copyrightNotice, GssDefines gssDefines, GssNonStandardFunctions gssNonStandardFunctions, GssUnrecognizeProperties gssUnrecognizeProperties, GssAtRules gssAtRules, GssExcludedClasses gssExcludedClasses, boolean build, boolean allowUnrecognizedFunctions, boolean allowUnrecognizedProperties, String cssRenamingPrefix, String outputRenamingMap, GssRenamingType renamingType, GssVendor vendor, GssOutputRenamingMapFormat outputRenamingMapFormat, GssOptimizationLevel optimizationLevel, GssOutputFormat outputFormat, GssInputOrientation inputOrientation, GssOutputOrientation outputOrientation) {
        super();
        this.info.set(info);
        this.buildpath.set(buildpath);
        this.output.set(output);
        this.copyrightNotice.set(copyrightNotice);
        this.gssDefines.set(gssDefines);
        this.gssNonStandardFunctions.set(gssNonStandardFunctions);
        this.gssUnrecognizeProperties.set(gssUnrecognizeProperties);
        this.gssAtRules.set(gssAtRules);
        this.gssExcludedClasses.set(gssExcludedClasses);
        this.build.set(build);
        this.allowUnrecognizedFunctions.set(allowUnrecognizedFunctions);
        this.allowUnrecognizedProperties.set(allowUnrecognizedProperties);
        this.cssRenamingPrefix.set(cssRenamingPrefix);
        this.outputRenamingMap.set(outputRenamingMap);
        this.renamingType.set(renamingType);
        this.vendor.set(vendor);
        this.outputRenamingMapFormat.set(outputRenamingMapFormat);
        this.optimizationLevel.set(optimizationLevel);
        this.outputFormat.set(outputFormat);
        this.inputOrientation.set(inputOrientation);
        this.outputOrientation.set(outputOrientation);
    }

    public ObjectProperty<Info> infoProperty() {
        return info;
    }

    public ObjectProperty<Buildpath> buildpathProperty() {
        return buildpath;
    }

    public ObjectProperty<Output> outputProperty() {
        return output;
    }

    public StringProperty copyrightNoticeProperty() {
        return copyrightNotice;
    }

    public ObjectProperty<GssDefines> gssDefinesProperty() {
        return gssDefines;
    }

    public ObjectProperty<GssNonStandardFunctions> gssNonStandardFunctionsProperty() {
        return gssNonStandardFunctions;
    }

    public ObjectProperty<GssUnrecognizeProperties> gssUnrecognizePropertiesProperty() {
        return gssUnrecognizeProperties;
    }

    public ObjectProperty<GssAtRules> gssAtRulesProperty() {
        return gssAtRules;
    }

    public ObjectProperty<GssExcludedClasses> gssExcludedClassesProperty() {
        return gssExcludedClasses;
    }

    public BooleanProperty buildProperty() {
        return build;
    }

    public BooleanProperty allowUnrecognizedFunctionsProperty() {
        return allowUnrecognizedFunctions;
    }

    public BooleanProperty allowUnrecognizedPropertiesProperty() {
        return allowUnrecognizedProperties;
    }

    public StringProperty cssRenamingPrefixProperty() {
        return cssRenamingPrefix;
    }

    public StringProperty outputRenamingMapProperty() {
        return outputRenamingMap;
    }

    public ObjectProperty<GssRenamingType> renamingTypeProperty() {
        return renamingType;
    }

    public ObjectProperty<GssVendor> vendorProperty() {
        return vendor;
    }

    public ObjectProperty<GssOutputRenamingMapFormat> outputRenamingMapFormatProperty() {
        return outputRenamingMapFormat;
    }

    public ObjectProperty<GssOptimizationLevel> optimizationLevelProperty() {
        return optimizationLevel;
    }

    public ObjectProperty<GssOutputFormat> outputFormatProperty() {
        return outputFormat;
    }

    public ObjectProperty<GssInputOrientation> inputOrientationProperty() {
        return inputOrientation;
    }

    public ObjectProperty<GssOutputOrientation> outputOrientationProperty() {
        return outputOrientation;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfClosureGss(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(6) : 0;
        if (this.getInfo() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            size += this.getInfo().sizeOfInfo(false);
        }
        if (this.getBuildpath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(2);
            size += this.getBuildpath().sizeOfBuildpath(false);
        }
        if (this.getOutput() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(3);
            size += this.getOutput().sizeOfOutput(false);
        }
        if (this.getCopyrightNotice() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(4);
            size += ZippyBuffer.sizeOfString(this.getCopyrightNotice());
        }
        if (this.getGssDefines() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(5);
            size += this.getGssDefines().sizeOfGssDefines(false);
        }
        if (this.getGssNonStandardFunctions() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(6);
            size += this.getGssNonStandardFunctions().sizeOfGssNonStandardFunctions(false);
        }
        if (this.getGssUnrecognizeProperties() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(7);
            size += this.getGssUnrecognizeProperties().sizeOfGssUnrecognizeProperties(false);
        }
        if (this.getGssAtRules() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(8);
            size += this.getGssAtRules().sizeOfGssAtRules(false);
        }
        if (this.getGssExcludedClasses() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(9);
            size += this.getGssExcludedClasses().sizeOfGssExcludedClasses(false);
        }
        size += ZippyBuffer.sizeOfRawVarInt(10);
        size += ZippyBuffer.sizeOfBoolean(this.isBuild());
        size += ZippyBuffer.sizeOfRawVarInt(11);
        size += ZippyBuffer.sizeOfBoolean(this.isAllowUnrecognizedFunctions());
        size += ZippyBuffer.sizeOfRawVarInt(12);
        size += ZippyBuffer.sizeOfBoolean(this.isAllowUnrecognizedProperties());
        if (this.getCssRenamingPrefix() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(13);
            size += ZippyBuffer.sizeOfString(this.getCssRenamingPrefix());
        }
        if (this.getOutputRenamingMap() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(14);
            size += ZippyBuffer.sizeOfString(this.getOutputRenamingMap());
        }
        if (this.getRenamingType() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(15);
            size += this.getRenamingType().sizeOfGssRenamingType();
        }
        if (this.getVendor() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(16);
            size += this.getVendor().sizeOfGssVendor();
        }
        if (this.getOutputRenamingMapFormat() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(17);
            size += this.getOutputRenamingMapFormat().sizeOfGssOutputRenamingMapFormat();
        }
        if (this.getOptimizationLevel() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(18);
            size += this.getOptimizationLevel().sizeOfGssOptimizationLevel();
        }
        if (this.getOutputFormat() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(19);
            size += this.getOutputFormat().sizeOfGssOutputFormat();
        }
        if (this.getInputOrientation() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(20);
            size += this.getInputOrientation().sizeOfGssInputOrientation();
        }
        if (this.getOutputOrientation() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(21);
            size += this.getOutputOrientation().sizeOfGssOutputOrientation();
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeClosureGss(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(6);
        }
        if (this.getInfo() != null) {
            writer.writeRawVarInt(1);
            this.getInfo().writeInfo(writer, false);
        }
        if (this.getBuildpath() != null) {
            writer.writeRawVarInt(2);
            this.getBuildpath().writeBuildpath(writer, false);
        }
        if (this.getOutput() != null) {
            writer.writeRawVarInt(3);
            this.getOutput().writeOutput(writer, false);
        }
        if (this.getCopyrightNotice() != null) {
            writer.writeRawVarInt(4);
            writer.writeString(this.getCopyrightNotice());
        }
        if (this.getGssDefines() != null) {
            writer.writeRawVarInt(5);
            this.getGssDefines().writeGssDefines(writer, false);
        }
        if (this.getGssNonStandardFunctions() != null) {
            writer.writeRawVarInt(6);
            this.getGssNonStandardFunctions().writeGssNonStandardFunctions(writer, false);
        }
        if (this.getGssUnrecognizeProperties() != null) {
            writer.writeRawVarInt(7);
            this.getGssUnrecognizeProperties().writeGssUnrecognizeProperties(writer, false);
        }
        if (this.getGssAtRules() != null) {
            writer.writeRawVarInt(8);
            this.getGssAtRules().writeGssAtRules(writer, false);
        }
        if (this.getGssExcludedClasses() != null) {
            writer.writeRawVarInt(9);
            this.getGssExcludedClasses().writeGssExcludedClasses(writer, false);
        }
        writer.writeRawVarInt(10);
        writer.writeBoolean(this.isBuild());
        writer.writeRawVarInt(11);
        writer.writeBoolean(this.isAllowUnrecognizedFunctions());
        writer.writeRawVarInt(12);
        writer.writeBoolean(this.isAllowUnrecognizedProperties());
        if (this.getCssRenamingPrefix() != null) {
            writer.writeRawVarInt(13);
            writer.writeString(this.getCssRenamingPrefix());
        }
        if (this.getOutputRenamingMap() != null) {
            writer.writeRawVarInt(14);
            writer.writeString(this.getOutputRenamingMap());
        }
        if (this.getRenamingType() != null) {
            writer.writeRawVarInt(15);
            this.getRenamingType().writeGssRenamingType(writer);
        }
        if (this.getVendor() != null) {
            writer.writeRawVarInt(16);
            this.getVendor().writeGssVendor(writer);
        }
        if (this.getOutputRenamingMapFormat() != null) {
            writer.writeRawVarInt(17);
            this.getOutputRenamingMapFormat().writeGssOutputRenamingMapFormat(writer);
        }
        if (this.getOptimizationLevel() != null) {
            writer.writeRawVarInt(18);
            this.getOptimizationLevel().writeGssOptimizationLevel(writer);
        }
        if (this.getOutputFormat() != null) {
            writer.writeRawVarInt(19);
            this.getOutputFormat().writeGssOutputFormat(writer);
        }
        if (this.getInputOrientation() != null) {
            writer.writeRawVarInt(20);
            this.getInputOrientation().writeGssInputOrientation(writer);
        }
        if (this.getOutputOrientation() != null) {
            writer.writeRawVarInt(21);
            this.getOutputOrientation().writeGssOutputOrientation(writer);
        }
        writer.writeRawVarInt(0);
    }

    public static ClosureGss readClosureGss(final ZippyBuffer reader) throws IOException {
        final ClosureGss packet = new ClosureGss();
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
                    packet.setOutput(Output.readOutput(reader));
                    break;
                case 4:
                    packet.setCopyrightNotice(reader.readString());
                    break;
                case 5:
                    packet.setGssDefines(GssDefines.readGssDefines(reader));
                    break;
                case 6:
                    packet.setGssNonStandardFunctions(GssNonStandardFunctions.readGssNonStandardFunctions(reader));
                    break;
                case 7:
                    packet.setGssUnrecognizeProperties(GssUnrecognizeProperties.readGssUnrecognizeProperties(reader));
                    break;
                case 8:
                    packet.setGssAtRules(GssAtRules.readGssAtRules(reader));
                    break;
                case 9:
                    packet.setGssExcludedClasses(GssExcludedClasses.readGssExcludedClasses(reader));
                    break;
                case 10:
                    packet.setBuild(reader.readBoolean());
                    break;
                case 11:
                    packet.setAllowUnrecognizedFunctions(reader.readBoolean());
                    break;
                case 12:
                    packet.setAllowUnrecognizedProperties(reader.readBoolean());
                    break;
                case 13:
                    packet.setCssRenamingPrefix(reader.readString());
                    break;
                case 14:
                    packet.setOutputRenamingMap(reader.readString());
                    break;
                case 15:
                    packet.setRenamingType(GssRenamingType.readGssRenamingType(reader));
                    break;
                case 16:
                    packet.setVendor(GssVendor.readGssVendor(reader));
                    break;
                case 17:
                    packet.setOutputRenamingMapFormat(GssOutputRenamingMapFormat.readGssOutputRenamingMapFormat(reader));
                    break;
                case 18:
                    packet.setOptimizationLevel(GssOptimizationLevel.readGssOptimizationLevel(reader));
                    break;
                case 19:
                    packet.setOutputFormat(GssOutputFormat.readGssOutputFormat(reader));
                    break;
                case 20:
                    packet.setInputOrientation(GssInputOrientation.readGssInputOrientation(reader));
                    break;
                case 21:
                    packet.setOutputOrientation(GssOutputOrientation.readGssOutputOrientation(reader));
                    break;
            }
        }
        return packet;
    }

}
