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

public class ClosureJs extends Node {

    private ObjectProperty<Info> info = new SimpleObjectProperty<Info>(this, "info");

    private ObjectProperty<Buildpath> buildpath = new SimpleObjectProperty<Buildpath>(this, "buildpath");

    private ObjectProperty<Output> output = new SimpleObjectProperty<Output>(this, "output");

    private StringProperty outputWrapper = new SimpleStringProperty(this, "outputWrapper");

    private ObjectProperty<Warnings> warnings = new SimpleObjectProperty<Warnings>(this, "warnings");

    private ObjectProperty<Checks> checks = new SimpleObjectProperty<Checks>(this, "checks");

    private ObjectProperty<Optimizations> optimizations = new SimpleObjectProperty<Optimizations>(this, "optimizations");

    private ObjectProperty<JsDocs> jsDocs = new SimpleObjectProperty<JsDocs>(this, "jsDocs");

    private ObjectProperty<Language> language = new SimpleObjectProperty<Language>(this, "language");

    private ObjectProperty<JsDefines> jsDefines = new SimpleObjectProperty<JsDefines>(this, "jsDefines");

    private ObjectProperty<JsRenaming> renaming = new SimpleObjectProperty<JsRenaming>(this, "renaming");

    private BooleanProperty build = new SimpleBooleanProperty(this, "build");

    private BooleanProperty acceptConstKeyword = new SimpleBooleanProperty(this, "acceptConstKeyword");

    private BooleanProperty closureStyle = new SimpleBooleanProperty(this, "closureStyle");

    private BooleanProperty closurePass = new SimpleBooleanProperty(this, "closurePass");

    private BooleanProperty jqueryPass = new SimpleBooleanProperty(this, "jqueryPass");

    private BooleanProperty angularPass = new SimpleBooleanProperty(this, "angularPass");

    private StringProperty charset = new SimpleStringProperty(this, "charset");

    private BooleanProperty manageClosureDependencies = new SimpleBooleanProperty(this, "manageClosureDependencies");

    private BooleanProperty externExports = new SimpleBooleanProperty(this, "externExports");

    private BooleanProperty generateExports = new SimpleBooleanProperty(this, "generateExports");

    private StringProperty externExportsPath = new SimpleStringProperty(this, "externExportsPath");

    private StringProperty translationsFile = new SimpleStringProperty(this, "translationsFile");

    private StringProperty translationsProject = new SimpleStringProperty(this, "translationsProject");

    private StringProperty sourceMapFile = new SimpleStringProperty(this, "sourceMapFile");

    private ObjectProperty<JsSourceMapFormat> sourceMapFormat = new SimpleObjectProperty<JsSourceMapFormat>(this, "sourceMapFormat");

    private BooleanProperty printInputDelimeter = new SimpleBooleanProperty(this, "printInputDelimeter");

    private StringProperty inputDelimiter = new SimpleStringProperty(this, "inputDelimiter");

    private BooleanProperty lineBreaks = new SimpleBooleanProperty(this, "lineBreaks");

    private BooleanProperty lineBreaksAggressive = new SimpleBooleanProperty(this, "lineBreaksAggressive");

    private BooleanProperty singleQuotes = new SimpleBooleanProperty(this, "singleQuotes");

    private BooleanProperty prettyPrint = new SimpleBooleanProperty(this, "prettyPrint");

    private BooleanProperty skipAllPasses = new SimpleBooleanProperty(this, "skipAllPasses");

    private BooleanProperty functionsOnly = new SimpleBooleanProperty(this, "functionsOnly");

    private BooleanProperty debug = new SimpleBooleanProperty(this, "debug");

    private BooleanProperty devmode = new SimpleBooleanProperty(this, "devmode");

    public final Info getInfo() {
        return info.get();
    }

    public final void setInfo(Info info) {
        if (info != null) {
            info.parent = this;
        }
        this.info.set(info);
    }

    public final Buildpath getBuildpath() {
        return buildpath.get();
    }

    public final void setBuildpath(Buildpath buildpath) {
        if (buildpath != null) {
            buildpath.parent = this;
        }
        this.buildpath.set(buildpath);
    }

    public final Output getOutput() {
        return output.get();
    }

    public final void setOutput(Output output) {
        if (output != null) {
            output.parent = this;
        }
        this.output.set(output);
    }

    public final String getOutputWrapper() {
        return outputWrapper.get();
    }

    public final void setOutputWrapper(String outputWrapper) {
        this.outputWrapper.set(outputWrapper);
    }

    public final Warnings getWarnings() {
        return warnings.get();
    }

    public final void setWarnings(Warnings warnings) {
        if (warnings != null) {
            warnings.parent = this;
        }
        this.warnings.set(warnings);
    }

    public final Checks getChecks() {
        return checks.get();
    }

    public final void setChecks(Checks checks) {
        if (checks != null) {
            checks.parent = this;
        }
        this.checks.set(checks);
    }

    public final Optimizations getOptimizations() {
        return optimizations.get();
    }

    public final void setOptimizations(Optimizations optimizations) {
        if (optimizations != null) {
            optimizations.parent = this;
        }
        this.optimizations.set(optimizations);
    }

    public final JsDocs getJsDocs() {
        return jsDocs.get();
    }

    public final void setJsDocs(JsDocs jsDocs) {
        if (jsDocs != null) {
            jsDocs.parent = this;
        }
        this.jsDocs.set(jsDocs);
    }

    public final Language getLanguage() {
        return language.get();
    }

    public final void setLanguage(Language language) {
        if (language != null) {
            language.parent = this;
        }
        this.language.set(language);
    }

    public final JsDefines getJsDefines() {
        return jsDefines.get();
    }

    public final void setJsDefines(JsDefines jsDefines) {
        if (jsDefines != null) {
            jsDefines.parent = this;
        }
        this.jsDefines.set(jsDefines);
    }

    public final JsRenaming getRenaming() {
        return renaming.get();
    }

    public final void setRenaming(JsRenaming renaming) {
        if (renaming != null) {
            renaming.parent = this;
        }
        this.renaming.set(renaming);
    }

    public final boolean isBuild() {
        return build.get();
    }

    public final void setBuild(boolean build) {
        this.build.set(build);
    }

    public final boolean isAcceptConstKeyword() {
        return acceptConstKeyword.get();
    }

    public final void setAcceptConstKeyword(boolean acceptConstKeyword) {
        this.acceptConstKeyword.set(acceptConstKeyword);
    }

    public final boolean isClosureStyle() {
        return closureStyle.get();
    }

    public final void setClosureStyle(boolean closureStyle) {
        this.closureStyle.set(closureStyle);
    }

    public final boolean isClosurePass() {
        return closurePass.get();
    }

    public final void setClosurePass(boolean closurePass) {
        this.closurePass.set(closurePass);
    }

    public final boolean isJqueryPass() {
        return jqueryPass.get();
    }

    public final void setJqueryPass(boolean jqueryPass) {
        this.jqueryPass.set(jqueryPass);
    }

    public final boolean isAngularPass() {
        return angularPass.get();
    }

    public final void setAngularPass(boolean angularPass) {
        this.angularPass.set(angularPass);
    }

    public final String getCharset() {
        return charset.get();
    }

    public final void setCharset(String charset) {
        this.charset.set(charset);
    }

    public final boolean isManageClosureDependencies() {
        return manageClosureDependencies.get();
    }

    public final void setManageClosureDependencies(boolean manageClosureDependencies) {
        this.manageClosureDependencies.set(manageClosureDependencies);
    }

    public final boolean isExternExports() {
        return externExports.get();
    }

    public final void setExternExports(boolean externExports) {
        this.externExports.set(externExports);
    }

    public final boolean isGenerateExports() {
        return generateExports.get();
    }

    public final void setGenerateExports(boolean generateExports) {
        this.generateExports.set(generateExports);
    }

    public final String getExternExportsPath() {
        return externExportsPath.get();
    }

    public final void setExternExportsPath(String externExportsPath) {
        this.externExportsPath.set(externExportsPath);
    }

    public final String getTranslationsFile() {
        return translationsFile.get();
    }

    public final void setTranslationsFile(String translationsFile) {
        this.translationsFile.set(translationsFile);
    }

    public final String getTranslationsProject() {
        return translationsProject.get();
    }

    public final void setTranslationsProject(String translationsProject) {
        this.translationsProject.set(translationsProject);
    }

    public final String getSourceMapFile() {
        return sourceMapFile.get();
    }

    public final void setSourceMapFile(String sourceMapFile) {
        this.sourceMapFile.set(sourceMapFile);
    }

    public final JsSourceMapFormat getSourceMapFormat() {
        return sourceMapFormat.get();
    }

    public final void setSourceMapFormat(JsSourceMapFormat sourceMapFormat) {
        this.sourceMapFormat.set(sourceMapFormat);
    }

    public final boolean isPrintInputDelimeter() {
        return printInputDelimeter.get();
    }

    public final void setPrintInputDelimeter(boolean printInputDelimeter) {
        this.printInputDelimeter.set(printInputDelimeter);
    }

    public final String getInputDelimiter() {
        return inputDelimiter.get();
    }

    public final void setInputDelimiter(String inputDelimiter) {
        this.inputDelimiter.set(inputDelimiter);
    }

    public final boolean isLineBreaks() {
        return lineBreaks.get();
    }

    public final void setLineBreaks(boolean lineBreaks) {
        this.lineBreaks.set(lineBreaks);
    }

    public final boolean isLineBreaksAggressive() {
        return lineBreaksAggressive.get();
    }

    public final void setLineBreaksAggressive(boolean lineBreaksAggressive) {
        this.lineBreaksAggressive.set(lineBreaksAggressive);
    }

    public final boolean isSingleQuotes() {
        return singleQuotes.get();
    }

    public final void setSingleQuotes(boolean singleQuotes) {
        this.singleQuotes.set(singleQuotes);
    }

    public final boolean isPrettyPrint() {
        return prettyPrint.get();
    }

    public final void setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint.set(prettyPrint);
    }

    public final boolean isSkipAllPasses() {
        return skipAllPasses.get();
    }

    public final void setSkipAllPasses(boolean skipAllPasses) {
        this.skipAllPasses.set(skipAllPasses);
    }

    public final boolean isFunctionsOnly() {
        return functionsOnly.get();
    }

    public final void setFunctionsOnly(boolean functionsOnly) {
        this.functionsOnly.set(functionsOnly);
    }

    public final boolean isDebug() {
        return debug.get();
    }

    public final void setDebug(boolean debug) {
        this.debug.set(debug);
    }

    public final boolean isDevmode() {
        return devmode.get();
    }

    public final void setDevmode(boolean devmode) {
        this.devmode.set(devmode);
    }

    public ClosureJs() {
        super();
    }

    public ClosureJs(Info info, Buildpath buildpath, Output output, String outputWrapper, Warnings warnings, Checks checks, Optimizations optimizations, JsDocs jsDocs, Language language, JsDefines jsDefines, JsRenaming renaming, boolean build, boolean acceptConstKeyword, boolean closureStyle, boolean closurePass, boolean jqueryPass, boolean angularPass, String charset, boolean manageClosureDependencies, boolean externExports, boolean generateExports, String externExportsPath, String translationsFile, String translationsProject, String sourceMapFile, JsSourceMapFormat sourceMapFormat, boolean printInputDelimeter, String inputDelimiter, boolean lineBreaks, boolean lineBreaksAggressive, boolean singleQuotes, boolean prettyPrint, boolean skipAllPasses, boolean functionsOnly, boolean debug, boolean devmode) {
        super();
        this.info.set(info);
        this.buildpath.set(buildpath);
        this.output.set(output);
        this.outputWrapper.set(outputWrapper);
        this.warnings.set(warnings);
        this.checks.set(checks);
        this.optimizations.set(optimizations);
        this.jsDocs.set(jsDocs);
        this.language.set(language);
        this.jsDefines.set(jsDefines);
        this.renaming.set(renaming);
        this.build.set(build);
        this.acceptConstKeyword.set(acceptConstKeyword);
        this.closureStyle.set(closureStyle);
        this.closurePass.set(closurePass);
        this.jqueryPass.set(jqueryPass);
        this.angularPass.set(angularPass);
        this.charset.set(charset);
        this.manageClosureDependencies.set(manageClosureDependencies);
        this.externExports.set(externExports);
        this.generateExports.set(generateExports);
        this.externExportsPath.set(externExportsPath);
        this.translationsFile.set(translationsFile);
        this.translationsProject.set(translationsProject);
        this.sourceMapFile.set(sourceMapFile);
        this.sourceMapFormat.set(sourceMapFormat);
        this.printInputDelimeter.set(printInputDelimeter);
        this.inputDelimiter.set(inputDelimiter);
        this.lineBreaks.set(lineBreaks);
        this.lineBreaksAggressive.set(lineBreaksAggressive);
        this.singleQuotes.set(singleQuotes);
        this.prettyPrint.set(prettyPrint);
        this.skipAllPasses.set(skipAllPasses);
        this.functionsOnly.set(functionsOnly);
        this.debug.set(debug);
        this.devmode.set(devmode);
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

    public StringProperty outputWrapperProperty() {
        return outputWrapper;
    }

    public ObjectProperty<Warnings> warningsProperty() {
        return warnings;
    }

    public ObjectProperty<Checks> checksProperty() {
        return checks;
    }

    public ObjectProperty<Optimizations> optimizationsProperty() {
        return optimizations;
    }

    public ObjectProperty<JsDocs> jsDocsProperty() {
        return jsDocs;
    }

    public ObjectProperty<Language> languageProperty() {
        return language;
    }

    public ObjectProperty<JsDefines> jsDefinesProperty() {
        return jsDefines;
    }

    public ObjectProperty<JsRenaming> renamingProperty() {
        return renaming;
    }

    public BooleanProperty buildProperty() {
        return build;
    }

    public BooleanProperty acceptConstKeywordProperty() {
        return acceptConstKeyword;
    }

    public BooleanProperty closureStyleProperty() {
        return closureStyle;
    }

    public BooleanProperty closurePassProperty() {
        return closurePass;
    }

    public BooleanProperty jqueryPassProperty() {
        return jqueryPass;
    }

    public BooleanProperty angularPassProperty() {
        return angularPass;
    }

    public StringProperty charsetProperty() {
        return charset;
    }

    public BooleanProperty manageClosureDependenciesProperty() {
        return manageClosureDependencies;
    }

    public BooleanProperty externExportsProperty() {
        return externExports;
    }

    public BooleanProperty generateExportsProperty() {
        return generateExports;
    }

    public StringProperty externExportsPathProperty() {
        return externExportsPath;
    }

    public StringProperty translationsFileProperty() {
        return translationsFile;
    }

    public StringProperty translationsProjectProperty() {
        return translationsProject;
    }

    public StringProperty sourceMapFileProperty() {
        return sourceMapFile;
    }

    public ObjectProperty<JsSourceMapFormat> sourceMapFormatProperty() {
        return sourceMapFormat;
    }

    public BooleanProperty printInputDelimeterProperty() {
        return printInputDelimeter;
    }

    public StringProperty inputDelimiterProperty() {
        return inputDelimiter;
    }

    public BooleanProperty lineBreaksProperty() {
        return lineBreaks;
    }

    public BooleanProperty lineBreaksAggressiveProperty() {
        return lineBreaksAggressive;
    }

    public BooleanProperty singleQuotesProperty() {
        return singleQuotes;
    }

    public BooleanProperty prettyPrintProperty() {
        return prettyPrint;
    }

    public BooleanProperty skipAllPassesProperty() {
        return skipAllPasses;
    }

    public BooleanProperty functionsOnlyProperty() {
        return functionsOnly;
    }

    public BooleanProperty debugProperty() {
        return debug;
    }

    public BooleanProperty devmodeProperty() {
        return devmode;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfClosureJs(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(7) : 0;
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
        if (this.getOutputWrapper() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(4);
            size += ZippyBuffer.sizeOfString(this.getOutputWrapper());
        }
        if (this.getWarnings() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(5);
            size += this.getWarnings().sizeOfWarnings(false);
        }
        if (this.getChecks() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(6);
            size += this.getChecks().sizeOfChecks(false);
        }
        if (this.getOptimizations() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(7);
            size += this.getOptimizations().sizeOfOptimizations(false);
        }
        if (this.getJsDocs() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(8);
            size += this.getJsDocs().sizeOfJsDocs(false);
        }
        if (this.getLanguage() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(9);
            size += this.getLanguage().sizeOfLanguage(false);
        }
        if (this.getJsDefines() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(10);
            size += this.getJsDefines().sizeOfJsDefines(false);
        }
        if (this.getRenaming() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(11);
            size += this.getRenaming().sizeOfJsRenaming(false);
        }
        size += ZippyBuffer.sizeOfRawVarInt(12);
        size += ZippyBuffer.sizeOfBoolean(this.isBuild());
        size += ZippyBuffer.sizeOfRawVarInt(13);
        size += ZippyBuffer.sizeOfBoolean(this.isAcceptConstKeyword());
        size += ZippyBuffer.sizeOfRawVarInt(14);
        size += ZippyBuffer.sizeOfBoolean(this.isClosureStyle());
        size += ZippyBuffer.sizeOfRawVarInt(15);
        size += ZippyBuffer.sizeOfBoolean(this.isClosurePass());
        size += ZippyBuffer.sizeOfRawVarInt(16);
        size += ZippyBuffer.sizeOfBoolean(this.isJqueryPass());
        size += ZippyBuffer.sizeOfRawVarInt(17);
        size += ZippyBuffer.sizeOfBoolean(this.isAngularPass());
        if (this.getCharset() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(18);
            size += ZippyBuffer.sizeOfString(this.getCharset());
        }
        size += ZippyBuffer.sizeOfRawVarInt(19);
        size += ZippyBuffer.sizeOfBoolean(this.isManageClosureDependencies());
        size += ZippyBuffer.sizeOfRawVarInt(20);
        size += ZippyBuffer.sizeOfBoolean(this.isExternExports());
        size += ZippyBuffer.sizeOfRawVarInt(21);
        size += ZippyBuffer.sizeOfBoolean(this.isGenerateExports());
        if (this.getExternExportsPath() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(22);
            size += ZippyBuffer.sizeOfString(this.getExternExportsPath());
        }
        if (this.getTranslationsFile() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(23);
            size += ZippyBuffer.sizeOfString(this.getTranslationsFile());
        }
        if (this.getTranslationsProject() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(24);
            size += ZippyBuffer.sizeOfString(this.getTranslationsProject());
        }
        if (this.getSourceMapFile() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(25);
            size += ZippyBuffer.sizeOfString(this.getSourceMapFile());
        }
        if (this.getSourceMapFormat() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(26);
            size += this.getSourceMapFormat().sizeOfJsSourceMapFormat();
        }
        size += ZippyBuffer.sizeOfRawVarInt(27);
        size += ZippyBuffer.sizeOfBoolean(this.isPrintInputDelimeter());
        if (this.getInputDelimiter() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(28);
            size += ZippyBuffer.sizeOfString(this.getInputDelimiter());
        }
        size += ZippyBuffer.sizeOfRawVarInt(29);
        size += ZippyBuffer.sizeOfBoolean(this.isLineBreaks());
        size += ZippyBuffer.sizeOfRawVarInt(30);
        size += ZippyBuffer.sizeOfBoolean(this.isLineBreaksAggressive());
        size += ZippyBuffer.sizeOfRawVarInt(31);
        size += ZippyBuffer.sizeOfBoolean(this.isSingleQuotes());
        size += ZippyBuffer.sizeOfRawVarInt(32);
        size += ZippyBuffer.sizeOfBoolean(this.isPrettyPrint());
        size += ZippyBuffer.sizeOfRawVarInt(33);
        size += ZippyBuffer.sizeOfBoolean(this.isSkipAllPasses());
        size += ZippyBuffer.sizeOfRawVarInt(34);
        size += ZippyBuffer.sizeOfBoolean(this.isFunctionsOnly());
        size += ZippyBuffer.sizeOfRawVarInt(35);
        size += ZippyBuffer.sizeOfBoolean(this.isDebug());
        size += ZippyBuffer.sizeOfRawVarInt(36);
        size += ZippyBuffer.sizeOfBoolean(this.isDevmode());
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeClosureJs(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(7);
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
        if (this.getOutputWrapper() != null) {
            writer.writeRawVarInt(4);
            writer.writeString(this.getOutputWrapper());
        }
        if (this.getWarnings() != null) {
            writer.writeRawVarInt(5);
            this.getWarnings().writeWarnings(writer, false);
        }
        if (this.getChecks() != null) {
            writer.writeRawVarInt(6);
            this.getChecks().writeChecks(writer, false);
        }
        if (this.getOptimizations() != null) {
            writer.writeRawVarInt(7);
            this.getOptimizations().writeOptimizations(writer, false);
        }
        if (this.getJsDocs() != null) {
            writer.writeRawVarInt(8);
            this.getJsDocs().writeJsDocs(writer, false);
        }
        if (this.getLanguage() != null) {
            writer.writeRawVarInt(9);
            this.getLanguage().writeLanguage(writer, false);
        }
        if (this.getJsDefines() != null) {
            writer.writeRawVarInt(10);
            this.getJsDefines().writeJsDefines(writer, false);
        }
        if (this.getRenaming() != null) {
            writer.writeRawVarInt(11);
            this.getRenaming().writeJsRenaming(writer, false);
        }
        writer.writeRawVarInt(12);
        writer.writeBoolean(this.isBuild());
        writer.writeRawVarInt(13);
        writer.writeBoolean(this.isAcceptConstKeyword());
        writer.writeRawVarInt(14);
        writer.writeBoolean(this.isClosureStyle());
        writer.writeRawVarInt(15);
        writer.writeBoolean(this.isClosurePass());
        writer.writeRawVarInt(16);
        writer.writeBoolean(this.isJqueryPass());
        writer.writeRawVarInt(17);
        writer.writeBoolean(this.isAngularPass());
        if (this.getCharset() != null) {
            writer.writeRawVarInt(18);
            writer.writeString(this.getCharset());
        }
        writer.writeRawVarInt(19);
        writer.writeBoolean(this.isManageClosureDependencies());
        writer.writeRawVarInt(20);
        writer.writeBoolean(this.isExternExports());
        writer.writeRawVarInt(21);
        writer.writeBoolean(this.isGenerateExports());
        if (this.getExternExportsPath() != null) {
            writer.writeRawVarInt(22);
            writer.writeString(this.getExternExportsPath());
        }
        if (this.getTranslationsFile() != null) {
            writer.writeRawVarInt(23);
            writer.writeString(this.getTranslationsFile());
        }
        if (this.getTranslationsProject() != null) {
            writer.writeRawVarInt(24);
            writer.writeString(this.getTranslationsProject());
        }
        if (this.getSourceMapFile() != null) {
            writer.writeRawVarInt(25);
            writer.writeString(this.getSourceMapFile());
        }
        if (this.getSourceMapFormat() != null) {
            writer.writeRawVarInt(26);
            this.getSourceMapFormat().writeJsSourceMapFormat(writer);
        }
        writer.writeRawVarInt(27);
        writer.writeBoolean(this.isPrintInputDelimeter());
        if (this.getInputDelimiter() != null) {
            writer.writeRawVarInt(28);
            writer.writeString(this.getInputDelimiter());
        }
        writer.writeRawVarInt(29);
        writer.writeBoolean(this.isLineBreaks());
        writer.writeRawVarInt(30);
        writer.writeBoolean(this.isLineBreaksAggressive());
        writer.writeRawVarInt(31);
        writer.writeBoolean(this.isSingleQuotes());
        writer.writeRawVarInt(32);
        writer.writeBoolean(this.isPrettyPrint());
        writer.writeRawVarInt(33);
        writer.writeBoolean(this.isSkipAllPasses());
        writer.writeRawVarInt(34);
        writer.writeBoolean(this.isFunctionsOnly());
        writer.writeRawVarInt(35);
        writer.writeBoolean(this.isDebug());
        writer.writeRawVarInt(36);
        writer.writeBoolean(this.isDevmode());
        writer.writeRawVarInt(0);
    }

    public static ClosureJs readClosureJs(final ZippyBuffer reader) throws IOException {
        final ClosureJs packet = new ClosureJs();
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
                    packet.setOutputWrapper(reader.readString());
                    break;
                case 5:
                    packet.setWarnings(Warnings.readWarnings(reader));
                    break;
                case 6:
                    packet.setChecks(Checks.readChecks(reader));
                    break;
                case 7:
                    packet.setOptimizations(Optimizations.readOptimizations(reader));
                    break;
                case 8:
                    packet.setJsDocs(JsDocs.readJsDocs(reader));
                    break;
                case 9:
                    packet.setLanguage(Language.readLanguage(reader));
                    break;
                case 10:
                    packet.setJsDefines(JsDefines.readJsDefines(reader));
                    break;
                case 11:
                    packet.setRenaming(JsRenaming.readJsRenaming(reader));
                    break;
                case 12:
                    packet.setBuild(reader.readBoolean());
                    break;
                case 13:
                    packet.setAcceptConstKeyword(reader.readBoolean());
                    break;
                case 14:
                    packet.setClosureStyle(reader.readBoolean());
                    break;
                case 15:
                    packet.setClosurePass(reader.readBoolean());
                    break;
                case 16:
                    packet.setJqueryPass(reader.readBoolean());
                    break;
                case 17:
                    packet.setAngularPass(reader.readBoolean());
                    break;
                case 18:
                    packet.setCharset(reader.readString());
                    break;
                case 19:
                    packet.setManageClosureDependencies(reader.readBoolean());
                    break;
                case 20:
                    packet.setExternExports(reader.readBoolean());
                    break;
                case 21:
                    packet.setGenerateExports(reader.readBoolean());
                    break;
                case 22:
                    packet.setExternExportsPath(reader.readString());
                    break;
                case 23:
                    packet.setTranslationsFile(reader.readString());
                    break;
                case 24:
                    packet.setTranslationsProject(reader.readString());
                    break;
                case 25:
                    packet.setSourceMapFile(reader.readString());
                    break;
                case 26:
                    packet.setSourceMapFormat(JsSourceMapFormat.readJsSourceMapFormat(reader));
                    break;
                case 27:
                    packet.setPrintInputDelimeter(reader.readBoolean());
                    break;
                case 28:
                    packet.setInputDelimiter(reader.readString());
                    break;
                case 29:
                    packet.setLineBreaks(reader.readBoolean());
                    break;
                case 30:
                    packet.setLineBreaksAggressive(reader.readBoolean());
                    break;
                case 31:
                    packet.setSingleQuotes(reader.readBoolean());
                    break;
                case 32:
                    packet.setPrettyPrint(reader.readBoolean());
                    break;
                case 33:
                    packet.setSkipAllPasses(reader.readBoolean());
                    break;
                case 34:
                    packet.setFunctionsOnly(reader.readBoolean());
                    break;
                case 35:
                    packet.setDebug(reader.readBoolean());
                    break;
                case 36:
                    packet.setDevmode(reader.readBoolean());
                    break;
            }
        }
        return packet;
    }

}
