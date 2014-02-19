package com.digiarea.closure.model.bind;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SingleSelectionModel;
import javafx.util.Callback;

import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.Checks;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.ClosureGss;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.ClosureSoy;
import com.digiarea.closure.model.ExcludeInputFilter;
import com.digiarea.closure.model.GssAtRule;
import com.digiarea.closure.model.GssAtRules;
import com.digiarea.closure.model.GssDefine;
import com.digiarea.closure.model.GssDefines;
import com.digiarea.closure.model.GssExcludedClass;
import com.digiarea.closure.model.GssExcludedClasses;
import com.digiarea.closure.model.GssInputOrientation;
import com.digiarea.closure.model.GssNonStandardFunction;
import com.digiarea.closure.model.GssNonStandardFunctions;
import com.digiarea.closure.model.GssOutputOrientation;
import com.digiarea.closure.model.GssOutputRenamingMapFormat;
import com.digiarea.closure.model.GssUnrecognizeProperties;
import com.digiarea.closure.model.GssUnrecognizeProperty;
import com.digiarea.closure.model.GssVendor;
import com.digiarea.closure.model.IncludeInputFilter;
import com.digiarea.closure.model.Info;
import com.digiarea.closure.model.InputFilterPattern;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefines;
import com.digiarea.closure.model.JsDoc;
import com.digiarea.closure.model.JsDocs;
import com.digiarea.closure.model.JsFunctionMap;
import com.digiarea.closure.model.JsPropertyMap;
import com.digiarea.closure.model.JsRenaming;
import com.digiarea.closure.model.JsRenamingFunctionPolice;
import com.digiarea.closure.model.JsRenamingPropertyPolice;
import com.digiarea.closure.model.JsRenamingVariablePolice;
import com.digiarea.closure.model.JsSourceMapFormat;
import com.digiarea.closure.model.JsVariableMap;
import com.digiarea.closure.model.LangType;
import com.digiarea.closure.model.Language;
import com.digiarea.closure.model.Optimization;
import com.digiarea.closure.model.Optimizations;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.SoyLocales;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.Warnings;
import com.digiarea.closure.model.controller.GSSCopySectionController;
import com.digiarea.closure.model.controller.GSSDefinesSectionController;
import com.digiarea.closure.model.controller.GSSFormattingSectionController;
import com.digiarea.closure.model.controller.GSSInfoSectionController;
import com.digiarea.closure.model.controller.GSSLibrariesSectionController;
import com.digiarea.closure.model.controller.GSSLintingSectionController;
import com.digiarea.closure.model.controller.GSSOrderSectionController;
import com.digiarea.closure.model.controller.GSSOutputSectionController;
import com.digiarea.closure.model.controller.GSSPageController;
import com.digiarea.closure.model.controller.GSSRenamingSectionController;
import com.digiarea.closure.model.controller.GSSSourceSectionController;
import com.digiarea.closure.model.controller.GSSVendorSectionController;
import com.digiarea.closure.model.controller.JSCheckSectionController;
import com.digiarea.closure.model.controller.JSDefinesSectionController;
import com.digiarea.closure.model.controller.JSDocsSectionController;
import com.digiarea.closure.model.controller.JSExportsSectionController;
import com.digiarea.closure.model.controller.JSFormattingSectionController;
import com.digiarea.closure.model.controller.JSInfoSectionController;
import com.digiarea.closure.model.controller.JSLanguageSectionController;
import com.digiarea.closure.model.controller.JSLibrariesSectionController;
import com.digiarea.closure.model.controller.JSOrderSectionController;
import com.digiarea.closure.model.controller.JSOutputSectionController;
import com.digiarea.closure.model.controller.JSPageController;
import com.digiarea.closure.model.controller.JSRenamingSectionController;
import com.digiarea.closure.model.controller.JSSourceMapSectionController;
import com.digiarea.closure.model.controller.JSSourceSectionController;
import com.digiarea.closure.model.controller.JSTranslationSectionController;
import com.digiarea.closure.model.controller.JSWarningsSectionController;
import com.digiarea.closure.model.controller.SOYInfoSectionController;
import com.digiarea.closure.model.controller.SOYLibrariesSectionController;
import com.digiarea.closure.model.controller.SOYLocalizationSectionController;
import com.digiarea.closure.model.controller.SOYOptionsSectionController;
import com.digiarea.closure.model.controller.SOYOrderSectionController;
import com.digiarea.closure.model.controller.SOYOutputSectionController;
import com.digiarea.closure.model.controller.SOYPageController;
import com.digiarea.closure.model.controller.SOYSourceSectionController;
import com.digiarea.closure.model.controller.SOYStyleSectionController;
import com.digiarea.closure.model.visitor.VoidVisitorAdapter;

public class Binder extends VoidVisitorAdapter<Object> {

    @Override
    public void visit(GssExcludedClasses n, Object ctx) throws Exception {
        super.visit(n, ctx);
        GSSRenamingSectionController gSSRenamingSectionController = (GSSRenamingSectionController) factory.call(GSSRenamingSectionController.class);
        gSSRenamingSectionController.getControlGssExcludedClass().itemsProperty().set(n.gssExcludedClassProperty().get());
        n.gssExcludedClassProperty().bindBidirectional(gSSRenamingSectionController.getControlGssExcludedClass().itemsProperty());
    }

    @Override
    public void visit(Warning n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(GssAtRule n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(GssUnrecognizeProperties n, Object ctx) throws Exception {
        super.visit(n, ctx);
        GSSLintingSectionController gSSLintingSectionController = (GSSLintingSectionController) factory.call(GSSLintingSectionController.class);
        gSSLintingSectionController.getControlGssUnrecognizeProperty().itemsProperty().set(n.gssUnrecognizePropertyProperty().get());
        n.gssUnrecognizePropertyProperty().bindBidirectional(gSSLintingSectionController.getControlGssUnrecognizeProperty().itemsProperty());
    }

    @Override
    public void visit(Optimizations n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSCheckSectionController jSCheckSectionController = (JSCheckSectionController) factory.call(JSCheckSectionController.class);
        jSCheckSectionController.getControlOptimization().itemsProperty().set(n.optimizationProperty().get());
        n.optimizationProperty().bindBidirectional(jSCheckSectionController.getControlOptimization().itemsProperty());
    }

    @Override
    public void visit(ClosureJs n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSPageController jSPageController = (JSPageController) factory.call(JSPageController.class);
        jSPageController.getControlBuild().selectedProperty().set(n.buildProperty().get());
        n.buildProperty().bindBidirectional(jSPageController.getControlBuild().selectedProperty());
        CustomBinder.bindBuild(n.buildProperty(), jSPageController.getTabs().disableProperty());
        CustomBinder.bindBuild(n.buildProperty(), jSPageController.getBtnRun().disableProperty());
        CustomBinder.bindBuildMessages(n.buildProperty(), jSPageController.getLabelMessageError().visibleProperty());
        CustomBinder.bindBuildMessages(n.buildProperty(), jSPageController.getLabelMessageWarning().visibleProperty());
        JSLanguageSectionController jSLanguageSectionController = (JSLanguageSectionController) factory.call(JSLanguageSectionController.class);
        jSLanguageSectionController.getControlAcceptConstKeyword().selectedProperty().set(n.acceptConstKeywordProperty().get());
        n.acceptConstKeywordProperty().bindBidirectional(jSLanguageSectionController.getControlAcceptConstKeyword().selectedProperty());
        jSLanguageSectionController.getControlClosureStyle().selectedProperty().set(n.closureStyleProperty().get());
        n.closureStyleProperty().bindBidirectional(jSLanguageSectionController.getControlClosureStyle().selectedProperty());
        jSLanguageSectionController.getControlClosurePass().selectedProperty().set(n.closurePassProperty().get());
        n.closurePassProperty().bindBidirectional(jSLanguageSectionController.getControlClosurePass().selectedProperty());
        jSLanguageSectionController.getControlJqueryPass().selectedProperty().set(n.jqueryPassProperty().get());
        n.jqueryPassProperty().bindBidirectional(jSLanguageSectionController.getControlJqueryPass().selectedProperty());
        jSLanguageSectionController.getControlAngularPass().selectedProperty().set(n.angularPassProperty().get());
        n.angularPassProperty().bindBidirectional(jSLanguageSectionController.getControlAngularPass().selectedProperty());
        jSLanguageSectionController.getControlCharset().textProperty().set(n.charsetProperty().get());
        n.charsetProperty().bindBidirectional(jSLanguageSectionController.getControlCharset().textProperty());
        JSExportsSectionController jSExportsSectionController = (JSExportsSectionController) factory.call(JSExportsSectionController.class);
        jSExportsSectionController.getControlExternExports().selectedProperty().set(n.externExportsProperty().get());
        n.externExportsProperty().bindBidirectional(jSExportsSectionController.getControlExternExports().selectedProperty());
        CustomBinder.bindJSGenerateExportsPath(n.externExportsProperty(), jSExportsSectionController.getControlExternExportsPath().disableProperty());
        CustomBinder.bindJSGenerateExportsButton(n.externExportsProperty(), jSExportsSectionController.getBtnBrowse().disableProperty());
        CustomBinder.bindJSGenerateExportsButton(n.externExportsProperty(), jSExportsSectionController.getBtnExternalBrowse().disableProperty());
        jSExportsSectionController.getControlGenerateExports().selectedProperty().set(n.generateExportsProperty().get());
        n.generateExportsProperty().bindBidirectional(jSExportsSectionController.getControlGenerateExports().selectedProperty());
        jSExportsSectionController.getControlExternExportsPath().textProperty().set(n.externExportsPathProperty().get());
        n.externExportsPathProperty().bindBidirectional(jSExportsSectionController.getControlExternExportsPath().textProperty());
        JSTranslationSectionController jSTranslationSectionController = (JSTranslationSectionController) factory.call(JSTranslationSectionController.class);
        jSTranslationSectionController.getControlTranslationsFile().textProperty().set(n.translationsFileProperty().get());
        n.translationsFileProperty().bindBidirectional(jSTranslationSectionController.getControlTranslationsFile().textProperty());
        jSTranslationSectionController.getControlTranslationsProject().textProperty().set(n.translationsProjectProperty().get());
        n.translationsProjectProperty().bindBidirectional(jSTranslationSectionController.getControlTranslationsProject().textProperty());
        JSSourceMapSectionController jSSourceMapSectionController = (JSSourceMapSectionController) factory.call(JSSourceMapSectionController.class);
        jSSourceMapSectionController.getControlSourceMapFile().textProperty().set(n.sourceMapFileProperty().get());
        n.sourceMapFileProperty().bindBidirectional(jSSourceMapSectionController.getControlSourceMapFile().textProperty());
        {
            final ObjectProperty<JsSourceMapFormat> model = n.sourceMapFormatProperty();
            final ObjectProperty<SingleSelectionModel<JsSourceMapFormat>> view = jSSourceMapSectionController.getControlSourceMapFormat().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<JsSourceMapFormat>() {

                @Override
                public void changed(ObservableValue<? extends JsSourceMapFormat> arg0, JsSourceMapFormat arg1, JsSourceMapFormat arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<JsSourceMapFormat>() {

                @Override
                public void changed(ObservableValue<? extends JsSourceMapFormat> arg0, JsSourceMapFormat arg1, JsSourceMapFormat arg2) {
                    model.set(arg2);
                }
            });
        }
        JSFormattingSectionController jSFormattingSectionController = (JSFormattingSectionController) factory.call(JSFormattingSectionController.class);
        jSFormattingSectionController.getControlPrintInputDelimeter().selectedProperty().set(n.printInputDelimeterProperty().get());
        n.printInputDelimeterProperty().bindBidirectional(jSFormattingSectionController.getControlPrintInputDelimeter().selectedProperty());
        CustomBinder.bindJSInputDelimiterPath(n.printInputDelimeterProperty(), jSFormattingSectionController.getControlInputDelimiter().disableProperty());
        jSFormattingSectionController.getControlInputDelimiter().textProperty().set(n.inputDelimiterProperty().get());
        n.inputDelimiterProperty().bindBidirectional(jSFormattingSectionController.getControlInputDelimiter().textProperty());
        jSFormattingSectionController.getControlLineBreaks().selectedProperty().set(n.lineBreaksProperty().get());
        n.lineBreaksProperty().bindBidirectional(jSFormattingSectionController.getControlLineBreaks().selectedProperty());
        jSFormattingSectionController.getControlLineBreaksAggressive().selectedProperty().set(n.lineBreaksAggressiveProperty().get());
        n.lineBreaksAggressiveProperty().bindBidirectional(jSFormattingSectionController.getControlLineBreaksAggressive().selectedProperty());
        jSFormattingSectionController.getControlSingleQuotes().selectedProperty().set(n.singleQuotesProperty().get());
        n.singleQuotesProperty().bindBidirectional(jSFormattingSectionController.getControlSingleQuotes().selectedProperty());
        jSFormattingSectionController.getControlPrettyPrint().selectedProperty().set(n.prettyPrintProperty().get());
        n.prettyPrintProperty().bindBidirectional(jSFormattingSectionController.getControlPrettyPrint().selectedProperty());
        JSCheckSectionController jSCheckSectionController = (JSCheckSectionController) factory.call(JSCheckSectionController.class);
        jSCheckSectionController.getControlSkipAllPasses().selectedProperty().set(n.skipAllPassesProperty().get());
        n.skipAllPassesProperty().bindBidirectional(jSCheckSectionController.getControlSkipAllPasses().selectedProperty());
        CustomBinder.bindChecksTable(n.skipAllPassesProperty(), jSCheckSectionController.getControlCheck().disableProperty());
        CustomBinder.bindOptimizationTable(n.skipAllPassesProperty(), jSCheckSectionController.getControlOptimization().disableProperty());
        CustomBinder.bindPerformChecks(n.skipAllPassesProperty(), jSCheckSectionController.getControlPerformCheck().selectedProperty());
        jSCheckSectionController.getControlFunctionsOnly().selectedProperty().set(n.functionsOnlyProperty().get());
        n.functionsOnlyProperty().bindBidirectional(jSCheckSectionController.getControlFunctionsOnly().selectedProperty());
        CustomBinder.bindOptimizationTable(n.functionsOnlyProperty(), jSCheckSectionController.getControlOptimization().disableProperty());
        CustomBinder.bindChecksTable(n.functionsOnlyProperty(), jSCheckSectionController.getControlCheck().disableProperty());
        CustomBinder.bindPerformChecks(n.functionsOnlyProperty(), jSCheckSectionController.getControlPerformCheck().selectedProperty());
    }

    @Override
    public void visit(JsFunctionMap n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSRenamingSectionController jSRenamingSectionController = (JSRenamingSectionController) factory.call(JSRenamingSectionController.class);
        jSRenamingSectionController.getControlFunctionInput().textProperty().set(n.inputProperty().get());
        n.inputProperty().bindBidirectional(jSRenamingSectionController.getControlFunctionInput().textProperty());
        jSRenamingSectionController.getControlFunctionOutput().textProperty().set(n.outputProperty().get());
        n.outputProperty().bindBidirectional(jSRenamingSectionController.getControlFunctionOutput().textProperty());
    }

    @Override
    public void visit(JsDoc n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(ClosureGss n, Object ctx) throws Exception {
        super.visit(n, ctx);
        GSSCopySectionController gSSCopySectionController = (GSSCopySectionController) factory.call(GSSCopySectionController.class);
        gSSCopySectionController.getControlCopyrightNotice().textProperty().set(n.copyrightNoticeProperty().get());
        n.copyrightNoticeProperty().bindBidirectional(gSSCopySectionController.getControlCopyrightNotice().textProperty());
        GSSPageController gSSPageController = (GSSPageController) factory.call(GSSPageController.class);
        gSSPageController.getControlBuild().selectedProperty().set(n.buildProperty().get());
        n.buildProperty().bindBidirectional(gSSPageController.getControlBuild().selectedProperty());
        CustomBinder.bindBuild(n.buildProperty(), gSSPageController.getTabs().disableProperty());
        CustomBinder.bindBuild(n.buildProperty(), gSSPageController.getBtnRun().disableProperty());
        CustomBinder.bindBuildMessages(n.buildProperty(), gSSPageController.getLabelMessageError().visibleProperty());
        CustomBinder.bindBuildMessages(n.buildProperty(), gSSPageController.getLabelMessageWarning().visibleProperty());
        GSSLintingSectionController gSSLintingSectionController = (GSSLintingSectionController) factory.call(GSSLintingSectionController.class);
        gSSLintingSectionController.getControlAllowUnrecognizedFunctions().selectedProperty().set(n.allowUnrecognizedFunctionsProperty().get());
        n.allowUnrecognizedFunctionsProperty().bindBidirectional(gSSLintingSectionController.getControlAllowUnrecognizedFunctions().selectedProperty());
        CustomBinder.bindUnrecognizedFunctionsTable(n.allowUnrecognizedFunctionsProperty(), gSSLintingSectionController.getControlGssNonStandardFunction().disableProperty());
        gSSLintingSectionController.getControlAllowUnrecognizedProperties().selectedProperty().set(n.allowUnrecognizedPropertiesProperty().get());
        n.allowUnrecognizedPropertiesProperty().bindBidirectional(gSSLintingSectionController.getControlAllowUnrecognizedProperties().selectedProperty());
        CustomBinder.bindUnrecognizedPropertiesTable(n.allowUnrecognizedPropertiesProperty(), gSSLintingSectionController.getControlGssUnrecognizeProperty().disableProperty());
        GSSRenamingSectionController gSSRenamingSectionController = (GSSRenamingSectionController) factory.call(GSSRenamingSectionController.class);
        gSSRenamingSectionController.getControlCssRenamingPrefix().textProperty().set(n.cssRenamingPrefixProperty().get());
        n.cssRenamingPrefixProperty().bindBidirectional(gSSRenamingSectionController.getControlCssRenamingPrefix().textProperty());
        gSSRenamingSectionController.getControlOutputRenamingMap().textProperty().set(n.outputRenamingMapProperty().get());
        n.outputRenamingMapProperty().bindBidirectional(gSSRenamingSectionController.getControlOutputRenamingMap().textProperty());
        CustomBinder.bindGssRenamingTypeNone(n.renamingTypeProperty(), gSSRenamingSectionController.getControlNone().selectedProperty());
        CustomBinder.bindGssRenamingTypeDebug(n.renamingTypeProperty(), gSSRenamingSectionController.getControlDebug().selectedProperty());
        CustomBinder.bindGssRenamingTypeClosure(n.renamingTypeProperty(), gSSRenamingSectionController.getControlClosure().selectedProperty());
        GSSVendorSectionController gSSVendorSectionController = (GSSVendorSectionController) factory.call(GSSVendorSectionController.class);
        {
            final ObjectProperty<GssVendor> model = n.vendorProperty();
            final ObjectProperty<SingleSelectionModel<GssVendor>> view = gSSVendorSectionController.getControlVendor().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<GssVendor>() {

                @Override
                public void changed(ObservableValue<? extends GssVendor> arg0, GssVendor arg1, GssVendor arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<GssVendor>() {

                @Override
                public void changed(ObservableValue<? extends GssVendor> arg0, GssVendor arg1, GssVendor arg2) {
                    model.set(arg2);
                }
            });
        }
        {
            final ObjectProperty<GssOutputRenamingMapFormat> model = n.outputRenamingMapFormatProperty();
            final ObjectProperty<SingleSelectionModel<GssOutputRenamingMapFormat>> view = gSSRenamingSectionController.getControlOutputRenamingMapFormat().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<GssOutputRenamingMapFormat>() {

                @Override
                public void changed(ObservableValue<? extends GssOutputRenamingMapFormat> arg0, GssOutputRenamingMapFormat arg1, GssOutputRenamingMapFormat arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<GssOutputRenamingMapFormat>() {

                @Override
                public void changed(ObservableValue<? extends GssOutputRenamingMapFormat> arg0, GssOutputRenamingMapFormat arg1, GssOutputRenamingMapFormat arg2) {
                    model.set(arg2);
                }
            });
        }
        GSSFormattingSectionController gSSFormattingSectionController = (GSSFormattingSectionController) factory.call(GSSFormattingSectionController.class);
        CustomBinder.bindGssOutputFormatCompressed(n.outputFormatProperty(), gSSFormattingSectionController.getControlCompressed().selectedProperty());
        CustomBinder.bindGssOutputFormatPrettyPrint(n.outputFormatProperty(), gSSFormattingSectionController.getControlPrettyPrint().selectedProperty());
        CustomBinder.bindGssOutputFormatDebug(n.outputFormatProperty(), gSSFormattingSectionController.getControlDebug().selectedProperty());
        {
            final ObjectProperty<GssInputOrientation> model = n.inputOrientationProperty();
            final ObjectProperty<SingleSelectionModel<GssInputOrientation>> view = gSSFormattingSectionController.getControlInputOrientation().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<GssInputOrientation>() {

                @Override
                public void changed(ObservableValue<? extends GssInputOrientation> arg0, GssInputOrientation arg1, GssInputOrientation arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<GssInputOrientation>() {

                @Override
                public void changed(ObservableValue<? extends GssInputOrientation> arg0, GssInputOrientation arg1, GssInputOrientation arg2) {
                    model.set(arg2);
                }
            });
        }
        {
            final ObjectProperty<GssOutputOrientation> model = n.outputOrientationProperty();
            final ObjectProperty<SingleSelectionModel<GssOutputOrientation>> view = gSSFormattingSectionController.getControlOutputOrientation().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<GssOutputOrientation>() {

                @Override
                public void changed(ObservableValue<? extends GssOutputOrientation> arg0, GssOutputOrientation arg1, GssOutputOrientation arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<GssOutputOrientation>() {

                @Override
                public void changed(ObservableValue<? extends GssOutputOrientation> arg0, GssOutputOrientation arg1, GssOutputOrientation arg2) {
                    model.set(arg2);
                }
            });
        }
    }

    @Override
    public void visit(Check n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(JsDocs n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSDocsSectionController jSDocsSectionController = (JSDocsSectionController) factory.call(JSDocsSectionController.class);
        jSDocsSectionController.getControlJsDoc().itemsProperty().set(n.jsDocProperty().get());
        n.jsDocProperty().bindBidirectional(jSDocsSectionController.getControlJsDoc().itemsProperty());
    }

    @Override
    public void visit(GssDefine n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(GssNonStandardFunctions n, Object ctx) throws Exception {
        super.visit(n, ctx);
        GSSLintingSectionController gSSLintingSectionController = (GSSLintingSectionController) factory.call(GSSLintingSectionController.class);
        gSSLintingSectionController.getControlGssNonStandardFunction().itemsProperty().set(n.gssNonStandardFunctionProperty().get());
        n.gssNonStandardFunctionProperty().bindBidirectional(gSSLintingSectionController.getControlGssNonStandardFunction().itemsProperty());
    }

    @Override
    public void visit(ClosureSoy n, Object ctx) throws Exception {
        super.visit(n, ctx);
        SOYOptionsSectionController sOYOptionsSectionController = (SOYOptionsSectionController) factory.call(SOYOptionsSectionController.class);
        {
            final ObjectProperty<SoyCssSchemeType> model = n.cssSchemeProperty();
            final ObjectProperty<SingleSelectionModel<SoyCssSchemeType>> view = sOYOptionsSectionController.getControlCssScheme().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<SoyCssSchemeType>() {

                @Override
                public void changed(ObservableValue<? extends SoyCssSchemeType> arg0, SoyCssSchemeType arg1, SoyCssSchemeType arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<SoyCssSchemeType>() {

                @Override
                public void changed(ObservableValue<? extends SoyCssSchemeType> arg0, SoyCssSchemeType arg1, SoyCssSchemeType arg2) {
                    model.set(arg2);
                }
            });
        }
        SOYStyleSectionController sOYStyleSectionController = (SOYStyleSectionController) factory.call(SOYStyleSectionController.class);
        CustomBinder.bindSoyCodeStyleBuilder(n.codeStyleProperty(), sOYStyleSectionController.getControlCodeStyleBuilder().selectedProperty());
        CustomBinder.bindSoyCodeStyleConcatination(n.codeStyleProperty(), sOYStyleSectionController.getControlCodeStyleConcat().selectedProperty());
        SOYPageController sOYPageController = (SOYPageController) factory.call(SOYPageController.class);
        sOYPageController.getControlBuild().selectedProperty().set(n.buildProperty().get());
        n.buildProperty().bindBidirectional(sOYPageController.getControlBuild().selectedProperty());
        CustomBinder.bindBuild(n.buildProperty(), sOYPageController.getTabs().disableProperty());
        CustomBinder.bindBuild(n.buildProperty(), sOYPageController.getBtnRun().disableProperty());
        CustomBinder.bindBuildMessages(n.buildProperty(), sOYPageController.getLabelMessageError().visibleProperty());
        CustomBinder.bindBuildMessages(n.buildProperty(), sOYPageController.getLabelMessageWarning().visibleProperty());
        sOYOptionsSectionController.getControlAllowExternalCalls().selectedProperty().set(n.allowExternalCallsProperty().get());
        n.allowExternalCallsProperty().bindBidirectional(sOYOptionsSectionController.getControlAllowExternalCalls().selectedProperty());
        sOYOptionsSectionController.getControlUsingIjData().selectedProperty().set(n.usingIjDataProperty().get());
        n.usingIjDataProperty().bindBidirectional(sOYOptionsSectionController.getControlUsingIjData().selectedProperty());
        sOYOptionsSectionController.getControlGenerateJsDoc().selectedProperty().set(n.generateJsDocProperty().get());
        n.generateJsDocProperty().bindBidirectional(sOYOptionsSectionController.getControlGenerateJsDoc().selectedProperty());
        sOYOptionsSectionController.getControlProvideRequireSoyNamespaces().selectedProperty().set(n.provideRequireSoyNamespacesProperty().get());
        n.provideRequireSoyNamespacesProperty().bindBidirectional(sOYOptionsSectionController.getControlProvideRequireSoyNamespaces().selectedProperty());
        sOYOptionsSectionController.getControlDeclareTopLevelNamespace().selectedProperty().set(n.declareTopLevelNamespaceProperty().get());
        n.declareTopLevelNamespaceProperty().bindBidirectional(sOYOptionsSectionController.getControlDeclareTopLevelNamespace().selectedProperty());
        sOYOptionsSectionController.getControlGenerateGoogMessagesDefs().selectedProperty().set(n.generateGoogMessagesDefsProperty().get());
        n.generateGoogMessagesDefsProperty().bindBidirectional(sOYOptionsSectionController.getControlGenerateGoogMessagesDefs().selectedProperty());
        sOYOptionsSectionController.getControlGoogMessagesExternal().selectedProperty().set(n.googMessagesExternalProperty().get());
        n.googMessagesExternalProperty().bindBidirectional(sOYOptionsSectionController.getControlGoogMessagesExternal().selectedProperty());
        sOYOptionsSectionController.getControlRightToLeftDir().selectedProperty().set(n.rightToLeftDirProperty().get());
        n.rightToLeftDirProperty().bindBidirectional(sOYOptionsSectionController.getControlRightToLeftDir().selectedProperty());
        sOYOptionsSectionController.getControlRightToLeftDirGoog().selectedProperty().set(n.rightToLeftDirGoogProperty().get());
        n.rightToLeftDirGoogProperty().bindBidirectional(sOYOptionsSectionController.getControlRightToLeftDirGoog().selectedProperty());
        sOYOptionsSectionController.getControlGlobalsPath().textProperty().set(n.globalsPathProperty().get());
        n.globalsPathProperty().bindBidirectional(sOYOptionsSectionController.getControlGlobalsPath().textProperty());
        SOYLocalizationSectionController sOYLocalizationSectionController = (SOYLocalizationSectionController) factory.call(SOYLocalizationSectionController.class);
        sOYLocalizationSectionController.getControlMessagesPath().textProperty().set(n.messagesPathProperty().get());
        n.messagesPathProperty().bindBidirectional(sOYLocalizationSectionController.getControlMessagesPath().textProperty());
        SOYOutputSectionController sOYOutputSectionController = (SOYOutputSectionController) factory.call(SOYOutputSectionController.class);
        sOYOutputSectionController.getControlOutputPath().textProperty().set(n.outputPathProperty().get());
        n.outputPathProperty().bindBidirectional(sOYOutputSectionController.getControlOutputPath().textProperty());
    }

    @Override
    public void visit(SoyLocale n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(ExcludeInputFilter n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(JsPropertyMap n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSRenamingSectionController jSRenamingSectionController = (JSRenamingSectionController) factory.call(JSRenamingSectionController.class);
        jSRenamingSectionController.getControlPropertyInput().textProperty().set(n.inputProperty().get());
        n.inputProperty().bindBidirectional(jSRenamingSectionController.getControlPropertyInput().textProperty());
        jSRenamingSectionController.getControlPropertyOutput().textProperty().set(n.outputProperty().get());
        n.outputProperty().bindBidirectional(jSRenamingSectionController.getControlPropertyOutput().textProperty());
    }

    @Override
    public void visit(Checks n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSCheckSectionController jSCheckSectionController = (JSCheckSectionController) factory.call(JSCheckSectionController.class);
        jSCheckSectionController.getControlCheck().itemsProperty().set(n.checkProperty().get());
        n.checkProperty().bindBidirectional(jSCheckSectionController.getControlCheck().itemsProperty());
    }

    @Override
    public void visit(IncludeInputFilter n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(GssDefines n, Object ctx) throws Exception {
        super.visit(n, ctx);
        GSSDefinesSectionController gSSDefinesSectionController = (GSSDefinesSectionController) factory.call(GSSDefinesSectionController.class);
        gSSDefinesSectionController.getControlGssDefine().itemsProperty().set(n.gssDefineProperty().get());
        n.gssDefineProperty().bindBidirectional(gSSDefinesSectionController.getControlGssDefine().itemsProperty());
    }

    @Override
    public void visit(Optimization n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Buildpath n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSSourceSectionController jSSourceSectionController = (JSSourceSectionController) factory.call(JSSourceSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSSourceSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(jSSourceSectionController.getControlSource().itemsProperty());
        }
        JSOrderSectionController jSOrderSectionController = (JSOrderSectionController) factory.call(JSOrderSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSOrderSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(jSOrderSectionController.getControlSource().itemsProperty());
        }
        JSLibrariesSectionController jSLibrariesSectionController = (JSLibrariesSectionController) factory.call(JSLibrariesSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSLibrariesSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(jSLibrariesSectionController.getControlSource().itemsProperty());
        }
        GSSSourceSectionController gSSSourceSectionController = (GSSSourceSectionController) factory.call(GSSSourceSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSSourceSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(gSSSourceSectionController.getControlSource().itemsProperty());
        }
        GSSOrderSectionController gSSOrderSectionController = (GSSOrderSectionController) factory.call(GSSOrderSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSOrderSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(gSSOrderSectionController.getControlSource().itemsProperty());
        }
        GSSLibrariesSectionController gSSLibrariesSectionController = (GSSLibrariesSectionController) factory.call(GSSLibrariesSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSLibrariesSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(gSSLibrariesSectionController.getControlSource().itemsProperty());
        }
        SOYSourceSectionController sOYSourceSectionController = (SOYSourceSectionController) factory.call(SOYSourceSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureSoy) {
            sOYSourceSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(sOYSourceSectionController.getControlSource().itemsProperty());
        }
        SOYOrderSectionController sOYOrderSectionController = (SOYOrderSectionController) factory.call(SOYOrderSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureSoy) {
            sOYOrderSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(sOYOrderSectionController.getControlSource().itemsProperty());
        }
        SOYLibrariesSectionController sOYLibrariesSectionController = (SOYLibrariesSectionController) factory.call(SOYLibrariesSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureSoy) {
            sOYLibrariesSectionController.getControlSource().itemsProperty().set(n.sourceProperty().get());
            n.sourceProperty().bindBidirectional(sOYLibrariesSectionController.getControlSource().itemsProperty());
        }
    }

    @Override
    public void visit(InputFilterPattern n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Warnings n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSWarningsSectionController jSWarningsSectionController = (JSWarningsSectionController) factory.call(JSWarningsSectionController.class);
        jSWarningsSectionController.getControlWarning().itemsProperty().set(n.warningProperty().get());
        n.warningProperty().bindBidirectional(jSWarningsSectionController.getControlWarning().itemsProperty());
    }

    @Override
    public void visit(JsRenaming n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSRenamingSectionController jSRenamingSectionController = (JSRenamingSectionController) factory.call(JSRenamingSectionController.class);
        {
            final ObjectProperty<JsRenamingVariablePolice> model = n.variablePoliceProperty();
            final ObjectProperty<SingleSelectionModel<JsRenamingVariablePolice>> view = jSRenamingSectionController.getControlVariablePolice().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<JsRenamingVariablePolice>() {

                @Override
                public void changed(ObservableValue<? extends JsRenamingVariablePolice> arg0, JsRenamingVariablePolice arg1, JsRenamingVariablePolice arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<JsRenamingVariablePolice>() {

                @Override
                public void changed(ObservableValue<? extends JsRenamingVariablePolice> arg0, JsRenamingVariablePolice arg1, JsRenamingVariablePolice arg2) {
                    model.set(arg2);
                }
            });
        }
        {
            final ObjectProperty<JsRenamingFunctionPolice> model = n.functionPoliceProperty();
            final ObjectProperty<SingleSelectionModel<JsRenamingFunctionPolice>> view = jSRenamingSectionController.getControlFunctionPolice().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<JsRenamingFunctionPolice>() {

                @Override
                public void changed(ObservableValue<? extends JsRenamingFunctionPolice> arg0, JsRenamingFunctionPolice arg1, JsRenamingFunctionPolice arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<JsRenamingFunctionPolice>() {

                @Override
                public void changed(ObservableValue<? extends JsRenamingFunctionPolice> arg0, JsRenamingFunctionPolice arg1, JsRenamingFunctionPolice arg2) {
                    model.set(arg2);
                }
            });
        }
        {
            final ObjectProperty<JsRenamingPropertyPolice> model = n.propertyPoliceProperty();
            final ObjectProperty<SingleSelectionModel<JsRenamingPropertyPolice>> view = jSRenamingSectionController.getControlPropertyPolice().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<JsRenamingPropertyPolice>() {

                @Override
                public void changed(ObservableValue<? extends JsRenamingPropertyPolice> arg0, JsRenamingPropertyPolice arg1, JsRenamingPropertyPolice arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<JsRenamingPropertyPolice>() {

                @Override
                public void changed(ObservableValue<? extends JsRenamingPropertyPolice> arg0, JsRenamingPropertyPolice arg1, JsRenamingPropertyPolice arg2) {
                    model.set(arg2);
                }
            });
        }
        jSRenamingSectionController.getControlDevirtualizePrototypeMethods().selectedProperty().set(n.devirtualizePrototypeMethodsProperty().get());
        n.devirtualizePrototypeMethodsProperty().bindBidirectional(jSRenamingSectionController.getControlDevirtualizePrototypeMethods().selectedProperty());
        jSRenamingSectionController.getControlGeneratePseudoNames().selectedProperty().set(n.generatePseudoNamesProperty().get());
        n.generatePseudoNamesProperty().bindBidirectional(jSRenamingSectionController.getControlGeneratePseudoNames().selectedProperty());
        jSRenamingSectionController.getControlShadowVariables().selectedProperty().set(n.shadowVariablesProperty().get());
        n.shadowVariablesProperty().bindBidirectional(jSRenamingSectionController.getControlShadowVariables().selectedProperty());
        jSRenamingSectionController.getControlPropertyAffinity().selectedProperty().set(n.propertyAffinityProperty().get());
        n.propertyAffinityProperty().bindBidirectional(jSRenamingSectionController.getControlPropertyAffinity().selectedProperty());
        jSRenamingSectionController.getControlDisambiguateProperties().selectedProperty().set(n.disambiguatePropertiesProperty().get());
        n.disambiguatePropertiesProperty().bindBidirectional(jSRenamingSectionController.getControlDisambiguateProperties().selectedProperty());
        jSRenamingSectionController.getControlAmbiguateProperties().selectedProperty().set(n.ambiguatePropertiesProperty().get());
        n.ambiguatePropertiesProperty().bindBidirectional(jSRenamingSectionController.getControlAmbiguateProperties().selectedProperty());
        jSRenamingSectionController.getControlExportTestFunctions().selectedProperty().set(n.exportTestFunctionsProperty().get());
        n.exportTestFunctionsProperty().bindBidirectional(jSRenamingSectionController.getControlExportTestFunctions().selectedProperty());
        jSRenamingSectionController.getControlRenameLabels().selectedProperty().set(n.renameLabelsProperty().get());
        n.renameLabelsProperty().bindBidirectional(jSRenamingSectionController.getControlRenameLabels().selectedProperty());
    }

    @Override
    public void visit(SoyLocales n, Object ctx) throws Exception {
        super.visit(n, ctx);
        SOYLocalizationSectionController sOYLocalizationSectionController = (SOYLocalizationSectionController) factory.call(SOYLocalizationSectionController.class);
        sOYLocalizationSectionController.getControlSoyLocale().itemsProperty().set(n.soyLocaleProperty().get());
        n.soyLocaleProperty().bindBidirectional(sOYLocalizationSectionController.getControlSoyLocale().itemsProperty());
    }

    @Override
    public void visit(GssAtRules n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(JsDefines n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSDefinesSectionController jSDefinesSectionController = (JSDefinesSectionController) factory.call(JSDefinesSectionController.class);
        jSDefinesSectionController.getControlJsDefine().itemsProperty().set(n.jsDefineProperty().get());
        n.jsDefineProperty().bindBidirectional(jSDefinesSectionController.getControlJsDefine().itemsProperty());
    }

    @Override
    public void visit(GssUnrecognizeProperty n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Language n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSLanguageSectionController jSLanguageSectionController = (JSLanguageSectionController) factory.call(JSLanguageSectionController.class);
        {
            final ObjectProperty<LangType> model = n.inputProperty();
            final ObjectProperty<SingleSelectionModel<LangType>> view = jSLanguageSectionController.getControlInputLanguage().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<LangType>() {

                @Override
                public void changed(ObservableValue<? extends LangType> arg0, LangType arg1, LangType arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<LangType>() {

                @Override
                public void changed(ObservableValue<? extends LangType> arg0, LangType arg1, LangType arg2) {
                    model.set(arg2);
                }
            });
        }
        {
            final ObjectProperty<LangType> model = n.outputProperty();
            final ObjectProperty<SingleSelectionModel<LangType>> view = jSLanguageSectionController.getControlOutputLanguage().selectionModelProperty();
            view.get().select(model.get());
            model.addListener(new ChangeListener<LangType>() {

                @Override
                public void changed(ObservableValue<? extends LangType> arg0, LangType arg1, LangType arg2) {
                    view.get().select(arg2);
                }
            });
            view.get().selectedItemProperty().addListener(new ChangeListener<LangType>() {

                @Override
                public void changed(ObservableValue<? extends LangType> arg0, LangType arg1, LangType arg2) {
                    model.set(arg2);
                }
            });
        }
    }

    @Override
    public void visit(Source n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Info n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSInfoSectionController jSInfoSectionController = (JSInfoSectionController) factory.call(JSInfoSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSInfoSectionController.getControlId().textProperty().set(n.idProperty().get());
            n.idProperty().bindBidirectional(jSInfoSectionController.getControlId().textProperty());
        }
        GSSInfoSectionController gSSInfoSectionController = (GSSInfoSectionController) factory.call(GSSInfoSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSInfoSectionController.getControlId().textProperty().set(n.idProperty().get());
            n.idProperty().bindBidirectional(gSSInfoSectionController.getControlId().textProperty());
        }
        SOYInfoSectionController sOYInfoSectionController = (SOYInfoSectionController) factory.call(SOYInfoSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureSoy) {
            sOYInfoSectionController.getControlId().textProperty().set(n.idProperty().get());
            n.idProperty().bindBidirectional(sOYInfoSectionController.getControlId().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSInfoSectionController.getControlVersion().textProperty().set(n.versionProperty().get());
            n.versionProperty().bindBidirectional(jSInfoSectionController.getControlVersion().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSInfoSectionController.getControlVersion().textProperty().set(n.versionProperty().get());
            n.versionProperty().bindBidirectional(gSSInfoSectionController.getControlVersion().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureSoy) {
            sOYInfoSectionController.getControlVersion().textProperty().set(n.versionProperty().get());
            n.versionProperty().bindBidirectional(sOYInfoSectionController.getControlVersion().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSInfoSectionController.getControlName().textProperty().set(n.nameProperty().get());
            n.nameProperty().bindBidirectional(jSInfoSectionController.getControlName().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSInfoSectionController.getControlName().textProperty().set(n.nameProperty().get());
            n.nameProperty().bindBidirectional(gSSInfoSectionController.getControlName().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureSoy) {
            sOYInfoSectionController.getControlName().textProperty().set(n.nameProperty().get());
            n.nameProperty().bindBidirectional(sOYInfoSectionController.getControlName().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSInfoSectionController.getControlVendor().textProperty().set(n.vendorProperty().get());
            n.vendorProperty().bindBidirectional(jSInfoSectionController.getControlVendor().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSInfoSectionController.getControlVendor().textProperty().set(n.vendorProperty().get());
            n.vendorProperty().bindBidirectional(gSSInfoSectionController.getControlVendor().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureSoy) {
            sOYInfoSectionController.getControlVendor().textProperty().set(n.vendorProperty().get());
            n.vendorProperty().bindBidirectional(sOYInfoSectionController.getControlVendor().textProperty());
        }
    }

    @Override
    public void visit(GssNonStandardFunction n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Closure n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(GssExcludedClass n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(JsDefine n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Output n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSOutputSectionController jSOutputSectionController = (JSOutputSectionController) factory.call(JSOutputSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSOutputSectionController.getControlPath().textProperty().set(n.pathProperty().get());
            n.pathProperty().bindBidirectional(jSOutputSectionController.getControlPath().textProperty());
        }
        GSSOutputSectionController gSSOutputSectionController = (GSSOutputSectionController) factory.call(GSSOutputSectionController.class);
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSOutputSectionController.getControlPath().textProperty().set(n.pathProperty().get());
            n.pathProperty().bindBidirectional(gSSOutputSectionController.getControlPath().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureJs) {
            jSOutputSectionController.getControlFile().textProperty().set(n.fileProperty().get());
            n.fileProperty().bindBidirectional(jSOutputSectionController.getControlFile().textProperty());
        }
        if (n.getParent() instanceof com.digiarea.closure.model.ClosureGss) {
            gSSOutputSectionController.getControlFile().textProperty().set(n.fileProperty().get());
            n.fileProperty().bindBidirectional(gSSOutputSectionController.getControlFile().textProperty());
        }
    }

    @Override
    public void visit(JsVariableMap n, Object ctx) throws Exception {
        super.visit(n, ctx);
        JSRenamingSectionController jSRenamingSectionController = (JSRenamingSectionController) factory.call(JSRenamingSectionController.class);
        jSRenamingSectionController.getControlVariableInput().textProperty().set(n.inputProperty().get());
        n.inputProperty().bindBidirectional(jSRenamingSectionController.getControlVariableInput().textProperty());
        jSRenamingSectionController.getControlVariableOutput().textProperty().set(n.outputProperty().get());
        n.outputProperty().bindBidirectional(jSRenamingSectionController.getControlVariableOutput().textProperty());
    }

    private Callback<Class<?>, Object> factory = null;

    public Binder(Callback<Class<?>, Object> factory) {
        this.factory = factory;
    }

    public Binder() {
        super();
    }

}
