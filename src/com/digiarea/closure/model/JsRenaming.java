package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class JsRenaming extends Node {

    private ObjectProperty<JsVariableMap> variableMap = new SimpleObjectProperty<JsVariableMap>(this, "variableMap");

    private ObjectProperty<JsFunctionMap> functionMap = new SimpleObjectProperty<JsFunctionMap>(this, "functionMap");

    private ObjectProperty<JsPropertyMap> propertyMap = new SimpleObjectProperty<JsPropertyMap>(this, "propertyMap");

    private ObjectProperty<JsRenamingVariablePolice> variablePolice = new SimpleObjectProperty<JsRenamingVariablePolice>(this, "variablePolice");

    private ObjectProperty<JsRenamingFunctionPolice> functionPolice = new SimpleObjectProperty<JsRenamingFunctionPolice>(this, "functionPolice");

    private ObjectProperty<JsRenamingPropertyPolice> propertyPolice = new SimpleObjectProperty<JsRenamingPropertyPolice>(this, "propertyPolice");

    private StringProperty prefix = new SimpleStringProperty(this, "prefix");

    private StringProperty prefixNamespace = new SimpleStringProperty(this, "prefixNamespace");

    private BooleanProperty devirtualizePrototypeMethods = new SimpleBooleanProperty(this, "devirtualizePrototypeMethods");

    private BooleanProperty generatePseudoNames = new SimpleBooleanProperty(this, "generatePseudoNames");

    private BooleanProperty shadowVariables = new SimpleBooleanProperty(this, "shadowVariables");

    private BooleanProperty propertyAffinity = new SimpleBooleanProperty(this, "propertyAffinity");

    private BooleanProperty disambiguateProperties = new SimpleBooleanProperty(this, "disambiguateProperties");

    private BooleanProperty ambiguateProperties = new SimpleBooleanProperty(this, "ambiguateProperties");

    private BooleanProperty exportTestFunctions = new SimpleBooleanProperty(this, "exportTestFunctions");

    private BooleanProperty renameLabels = new SimpleBooleanProperty(this, "renameLabels");

    public final JsVariableMap getVariableMap() {
        return variableMap.get();
    }

    public final void setVariableMap(JsVariableMap variableMap) {
        if (variableMap != null) {
            variableMap.parent = this;
        }
        this.variableMap.set(variableMap);
    }

    public final JsFunctionMap getFunctionMap() {
        return functionMap.get();
    }

    public final void setFunctionMap(JsFunctionMap functionMap) {
        if (functionMap != null) {
            functionMap.parent = this;
        }
        this.functionMap.set(functionMap);
    }

    public final JsPropertyMap getPropertyMap() {
        return propertyMap.get();
    }

    public final void setPropertyMap(JsPropertyMap propertyMap) {
        if (propertyMap != null) {
            propertyMap.parent = this;
        }
        this.propertyMap.set(propertyMap);
    }

    public final JsRenamingVariablePolice getVariablePolice() {
        return variablePolice.get();
    }

    public final void setVariablePolice(JsRenamingVariablePolice variablePolice) {
        this.variablePolice.set(variablePolice);
    }

    public final JsRenamingFunctionPolice getFunctionPolice() {
        return functionPolice.get();
    }

    public final void setFunctionPolice(JsRenamingFunctionPolice functionPolice) {
        this.functionPolice.set(functionPolice);
    }

    public final JsRenamingPropertyPolice getPropertyPolice() {
        return propertyPolice.get();
    }

    public final void setPropertyPolice(JsRenamingPropertyPolice propertyPolice) {
        this.propertyPolice.set(propertyPolice);
    }

    public final String getPrefix() {
        return prefix.get();
    }

    public final void setPrefix(String prefix) {
        this.prefix.set(prefix);
    }

    public final String getPrefixNamespace() {
        return prefixNamespace.get();
    }

    public final void setPrefixNamespace(String prefixNamespace) {
        this.prefixNamespace.set(prefixNamespace);
    }

    public final boolean isDevirtualizePrototypeMethods() {
        return devirtualizePrototypeMethods.get();
    }

    public final void setDevirtualizePrototypeMethods(boolean devirtualizePrototypeMethods) {
        this.devirtualizePrototypeMethods.set(devirtualizePrototypeMethods);
    }

    public final boolean isGeneratePseudoNames() {
        return generatePseudoNames.get();
    }

    public final void setGeneratePseudoNames(boolean generatePseudoNames) {
        this.generatePseudoNames.set(generatePseudoNames);
    }

    public final boolean isShadowVariables() {
        return shadowVariables.get();
    }

    public final void setShadowVariables(boolean shadowVariables) {
        this.shadowVariables.set(shadowVariables);
    }

    public final boolean isPropertyAffinity() {
        return propertyAffinity.get();
    }

    public final void setPropertyAffinity(boolean propertyAffinity) {
        this.propertyAffinity.set(propertyAffinity);
    }

    public final boolean isDisambiguateProperties() {
        return disambiguateProperties.get();
    }

    public final void setDisambiguateProperties(boolean disambiguateProperties) {
        this.disambiguateProperties.set(disambiguateProperties);
    }

    public final boolean isAmbiguateProperties() {
        return ambiguateProperties.get();
    }

    public final void setAmbiguateProperties(boolean ambiguateProperties) {
        this.ambiguateProperties.set(ambiguateProperties);
    }

    public final boolean isExportTestFunctions() {
        return exportTestFunctions.get();
    }

    public final void setExportTestFunctions(boolean exportTestFunctions) {
        this.exportTestFunctions.set(exportTestFunctions);
    }

    public final boolean isRenameLabels() {
        return renameLabels.get();
    }

    public final void setRenameLabels(boolean renameLabels) {
        this.renameLabels.set(renameLabels);
    }

    public JsRenaming() {
        super();
    }

    public JsRenaming(JsVariableMap variableMap, JsFunctionMap functionMap, JsPropertyMap propertyMap, JsRenamingVariablePolice variablePolice, JsRenamingFunctionPolice functionPolice, JsRenamingPropertyPolice propertyPolice, String prefix, String prefixNamespace, boolean devirtualizePrototypeMethods, boolean generatePseudoNames, boolean shadowVariables, boolean propertyAffinity, boolean disambiguateProperties, boolean ambiguateProperties, boolean exportTestFunctions, boolean renameLabels) {
        super();
        this.variableMap.set(variableMap);
        this.functionMap.set(functionMap);
        this.propertyMap.set(propertyMap);
        this.variablePolice.set(variablePolice);
        this.functionPolice.set(functionPolice);
        this.propertyPolice.set(propertyPolice);
        this.prefix.set(prefix);
        this.prefixNamespace.set(prefixNamespace);
        this.devirtualizePrototypeMethods.set(devirtualizePrototypeMethods);
        this.generatePseudoNames.set(generatePseudoNames);
        this.shadowVariables.set(shadowVariables);
        this.propertyAffinity.set(propertyAffinity);
        this.disambiguateProperties.set(disambiguateProperties);
        this.ambiguateProperties.set(ambiguateProperties);
        this.exportTestFunctions.set(exportTestFunctions);
        this.renameLabels.set(renameLabels);
    }

    public ObjectProperty<JsVariableMap> variableMapProperty() {
        return variableMap;
    }

    public ObjectProperty<JsFunctionMap> functionMapProperty() {
        return functionMap;
    }

    public ObjectProperty<JsPropertyMap> propertyMapProperty() {
        return propertyMap;
    }

    public ObjectProperty<JsRenamingVariablePolice> variablePoliceProperty() {
        return variablePolice;
    }

    public ObjectProperty<JsRenamingFunctionPolice> functionPoliceProperty() {
        return functionPolice;
    }

    public ObjectProperty<JsRenamingPropertyPolice> propertyPoliceProperty() {
        return propertyPolice;
    }

    public StringProperty prefixProperty() {
        return prefix;
    }

    public StringProperty prefixNamespaceProperty() {
        return prefixNamespace;
    }

    public BooleanProperty devirtualizePrototypeMethodsProperty() {
        return devirtualizePrototypeMethods;
    }

    public BooleanProperty generatePseudoNamesProperty() {
        return generatePseudoNames;
    }

    public BooleanProperty shadowVariablesProperty() {
        return shadowVariables;
    }

    public BooleanProperty propertyAffinityProperty() {
        return propertyAffinity;
    }

    public BooleanProperty disambiguatePropertiesProperty() {
        return disambiguateProperties;
    }

    public BooleanProperty ambiguatePropertiesProperty() {
        return ambiguateProperties;
    }

    public BooleanProperty exportTestFunctionsProperty() {
        return exportTestFunctions;
    }

    public BooleanProperty renameLabelsProperty() {
        return renameLabels;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfJsRenaming(final boolean isExternal) {
        int size = isExternal ? 1 : 0;
        if (this.getVariableMap() != null) {
            size += 1;
            size += this.getVariableMap().sizeOfJsVariableMap(false);
        }
        if (this.getFunctionMap() != null) {
            size += 1;
            size += this.getFunctionMap().sizeOfJsFunctionMap(false);
        }
        if (this.getPropertyMap() != null) {
            size += 1;
            size += this.getPropertyMap().sizeOfJsPropertyMap(false);
        }
        if (this.getVariablePolice() != null) {
            size += 1;
            size += this.getVariablePolice().sizeOfJsRenamingVariablePolice();
        }
        if (this.getFunctionPolice() != null) {
            size += 1;
            size += this.getFunctionPolice().sizeOfJsRenamingFunctionPolice();
        }
        if (this.getPropertyPolice() != null) {
            size += 1;
            size += this.getPropertyPolice().sizeOfJsRenamingPropertyPolice();
        }
        if (this.getPrefix() != null) {
            size += 1;
            size += ZippyBuffer.sizeOfString(this.getPrefix());
        }
        if (this.getPrefixNamespace() != null) {
            size += 1;
            size += ZippyBuffer.sizeOfString(this.getPrefixNamespace());
        }
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isDevirtualizePrototypeMethods());
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isGeneratePseudoNames());
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isShadowVariables());
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isPropertyAffinity());
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isDisambiguateProperties());
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isAmbiguateProperties());
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isExportTestFunctions());
        size += 1;
        size += ZippyBuffer.sizeOfBoolean(this.isRenameLabels());
        size += 1;
        return size;
    }

    public final void writeJsRenaming(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(39);
        }
        if (this.getVariableMap() != null) {
            writer.writeRawVarInt(1);
            this.getVariableMap().writeJsVariableMap(writer, false);
        }
        if (this.getFunctionMap() != null) {
            writer.writeRawVarInt(2);
            this.getFunctionMap().writeJsFunctionMap(writer, false);
        }
        if (this.getPropertyMap() != null) {
            writer.writeRawVarInt(3);
            this.getPropertyMap().writeJsPropertyMap(writer, false);
        }
        if (this.getVariablePolice() != null) {
            writer.writeRawVarInt(4);
            this.getVariablePolice().writeJsRenamingVariablePolice(writer);
        }
        if (this.getFunctionPolice() != null) {
            writer.writeRawVarInt(5);
            this.getFunctionPolice().writeJsRenamingFunctionPolice(writer);
        }
        if (this.getPropertyPolice() != null) {
            writer.writeRawVarInt(6);
            this.getPropertyPolice().writeJsRenamingPropertyPolice(writer);
        }
        if (this.getPrefix() != null) {
            writer.writeRawVarInt(7);
            writer.writeString(this.getPrefix());
        }
        if (this.getPrefixNamespace() != null) {
            writer.writeRawVarInt(8);
            writer.writeString(this.getPrefixNamespace());
        }
        writer.writeRawVarInt(9);
        writer.writeBoolean(this.isDevirtualizePrototypeMethods());
        writer.writeRawVarInt(10);
        writer.writeBoolean(this.isGeneratePseudoNames());
        writer.writeRawVarInt(11);
        writer.writeBoolean(this.isShadowVariables());
        writer.writeRawVarInt(12);
        writer.writeBoolean(this.isPropertyAffinity());
        writer.writeRawVarInt(13);
        writer.writeBoolean(this.isDisambiguateProperties());
        writer.writeRawVarInt(14);
        writer.writeBoolean(this.isAmbiguateProperties());
        writer.writeRawVarInt(15);
        writer.writeBoolean(this.isExportTestFunctions());
        writer.writeRawVarInt(16);
        writer.writeBoolean(this.isRenameLabels());
        writer.writeRawVarInt(0);
    }

    public static JsRenaming readJsRenaming(final ZippyBuffer reader) throws IOException {
        final JsRenaming packet = new JsRenaming();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setVariableMap(JsVariableMap.readJsVariableMap(reader));
                    break;
                case 2:
                    packet.setFunctionMap(JsFunctionMap.readJsFunctionMap(reader));
                    break;
                case 3:
                    packet.setPropertyMap(JsPropertyMap.readJsPropertyMap(reader));
                    break;
                case 4:
                    packet.setVariablePolice(JsRenamingVariablePolice.readJsRenamingVariablePolice(reader));
                    break;
                case 5:
                    packet.setFunctionPolice(JsRenamingFunctionPolice.readJsRenamingFunctionPolice(reader));
                    break;
                case 6:
                    packet.setPropertyPolice(JsRenamingPropertyPolice.readJsRenamingPropertyPolice(reader));
                    break;
                case 7:
                    packet.setPrefix(reader.readString());
                    break;
                case 8:
                    packet.setPrefixNamespace(reader.readString());
                    break;
                case 9:
                    packet.setDevirtualizePrototypeMethods(reader.readBoolean());
                    break;
                case 10:
                    packet.setGeneratePseudoNames(reader.readBoolean());
                    break;
                case 11:
                    packet.setShadowVariables(reader.readBoolean());
                    break;
                case 12:
                    packet.setPropertyAffinity(reader.readBoolean());
                    break;
                case 13:
                    packet.setDisambiguateProperties(reader.readBoolean());
                    break;
                case 14:
                    packet.setAmbiguateProperties(reader.readBoolean());
                    break;
                case 15:
                    packet.setExportTestFunctions(reader.readBoolean());
                    break;
                case 16:
                    packet.setRenameLabels(reader.readBoolean());
                    break;
            }
        }
        return packet;
    }

}
