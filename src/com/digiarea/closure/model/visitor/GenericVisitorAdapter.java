package com.digiarea.closure.model.visitor;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.Checks;
import com.digiarea.closure.model.CheckType;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.ClosureGss;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.ClosureSoy;
import com.digiarea.closure.model.ExcludeInputFilter;
import com.digiarea.closure.model.InputFilterPattern;
import com.digiarea.closure.model.GssAtRule;
import com.digiarea.closure.model.GssAtRules;
import com.digiarea.closure.model.GssDefine;
import com.digiarea.closure.model.GssDefines;
import com.digiarea.closure.model.GssExcludedClass;
import com.digiarea.closure.model.GssExcludedClasses;
import com.digiarea.closure.model.GssInputOrientation;
import com.digiarea.closure.model.GssNonStandardFunction;
import com.digiarea.closure.model.GssNonStandardFunctions;
import com.digiarea.closure.model.GssOptimizationLevel;
import com.digiarea.closure.model.GssOutputFormat;
import com.digiarea.closure.model.GssOutputOrientation;
import com.digiarea.closure.model.GssOutputRenamingMapFormat;
import com.digiarea.closure.model.GssRenamingType;
import com.digiarea.closure.model.GssUnrecognizeProperties;
import com.digiarea.closure.model.GssUnrecognizeProperty;
import com.digiarea.closure.model.GssVendor;
import com.digiarea.closure.model.IncludeInputFilter;
import com.digiarea.closure.model.Info;
import com.digiarea.closure.model.InputFilterType;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefines;
import com.digiarea.closure.model.JsDefineType;
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
import com.digiarea.closure.model.OptimizationType;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.SoyCodeStyle;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.SoyLocales;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.Warnings;
import com.digiarea.closure.model.WarningType;

public class GenericVisitorAdapter<R, C> implements GenericVisitor<R, C> {

    @Override
    public R visit(Buildpath n, C ctx) throws Exception {
        if (n.getSource() != null) {
            for (Source item : n.getSource()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(Check n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Checks n, C ctx) throws Exception {
        if (n.getCheck() != null) {
            for (Check item : n.getCheck()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(CheckType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Closure n, C ctx) throws Exception {
        if (n.getClosureJs() != null) {
            n.getClosureJs().accept(this, ctx);
        }
        if (n.getClosureGss() != null) {
            n.getClosureGss().accept(this, ctx);
        }
        if (n.getClosureSoy() != null) {
            n.getClosureSoy().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ClosureGss n, C ctx) throws Exception {
        if (n.getInfo() != null) {
            n.getInfo().accept(this, ctx);
        }
        if (n.getBuildpath() != null) {
            n.getBuildpath().accept(this, ctx);
        }
        if (n.getOutput() != null) {
            n.getOutput().accept(this, ctx);
        }
        if (n.getGssDefines() != null) {
            n.getGssDefines().accept(this, ctx);
        }
        if (n.getGssNonStandardFunctions() != null) {
            n.getGssNonStandardFunctions().accept(this, ctx);
        }
        if (n.getGssUnrecognizeProperties() != null) {
            n.getGssUnrecognizeProperties().accept(this, ctx);
        }
        if (n.getGssAtRules() != null) {
            n.getGssAtRules().accept(this, ctx);
        }
        if (n.getGssExcludedClasses() != null) {
            n.getGssExcludedClasses().accept(this, ctx);
        }
        if (n.getRenamingType() != null) {
            n.getRenamingType().accept(this, ctx);
        }
        if (n.getVendor() != null) {
            n.getVendor().accept(this, ctx);
        }
        if (n.getOutputRenamingMapFormat() != null) {
            n.getOutputRenamingMapFormat().accept(this, ctx);
        }
        if (n.getOptimizationLevel() != null) {
            n.getOptimizationLevel().accept(this, ctx);
        }
        if (n.getOutputFormat() != null) {
            n.getOutputFormat().accept(this, ctx);
        }
        if (n.getInputOrientation() != null) {
            n.getInputOrientation().accept(this, ctx);
        }
        if (n.getOutputOrientation() != null) {
            n.getOutputOrientation().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ClosureJs n, C ctx) throws Exception {
        if (n.getInfo() != null) {
            n.getInfo().accept(this, ctx);
        }
        if (n.getBuildpath() != null) {
            n.getBuildpath().accept(this, ctx);
        }
        if (n.getOutput() != null) {
            n.getOutput().accept(this, ctx);
        }
        if (n.getWarnings() != null) {
            n.getWarnings().accept(this, ctx);
        }
        if (n.getChecks() != null) {
            n.getChecks().accept(this, ctx);
        }
        if (n.getOptimizations() != null) {
            n.getOptimizations().accept(this, ctx);
        }
        if (n.getJsDocs() != null) {
            n.getJsDocs().accept(this, ctx);
        }
        if (n.getLanguage() != null) {
            n.getLanguage().accept(this, ctx);
        }
        if (n.getJsDefines() != null) {
            n.getJsDefines().accept(this, ctx);
        }
        if (n.getRenaming() != null) {
            n.getRenaming().accept(this, ctx);
        }
        if (n.getSourceMapFormat() != null) {
            n.getSourceMapFormat().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ClosureSoy n, C ctx) throws Exception {
        if (n.getInfo() != null) {
            n.getInfo().accept(this, ctx);
        }
        if (n.getBuildpath() != null) {
            n.getBuildpath().accept(this, ctx);
        }
        if (n.getSoyLocales() != null) {
            n.getSoyLocales().accept(this, ctx);
        }
        if (n.getCssScheme() != null) {
            n.getCssScheme().accept(this, ctx);
        }
        if (n.getCodeStyle() != null) {
            n.getCodeStyle().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(ExcludeInputFilter n, C ctx) throws Exception {
        if (n.getPattern() != null) {
            for (InputFilterPattern item : n.getPattern()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(GssAtRule n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssAtRules n, C ctx) throws Exception {
        if (n.getGssAtRule() != null) {
            for (GssAtRule item : n.getGssAtRule()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(GssDefine n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssDefines n, C ctx) throws Exception {
        if (n.getGssDefine() != null) {
            for (GssDefine item : n.getGssDefine()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(GssExcludedClass n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssExcludedClasses n, C ctx) throws Exception {
        if (n.getGssExcludedClass() != null) {
            for (GssExcludedClass item : n.getGssExcludedClass()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(GssInputOrientation n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssNonStandardFunction n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssNonStandardFunctions n, C ctx) throws Exception {
        if (n.getGssNonStandardFunction() != null) {
            for (GssNonStandardFunction item : n.getGssNonStandardFunction()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(GssOptimizationLevel n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssOutputFormat n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssOutputOrientation n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssOutputRenamingMapFormat n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssRenamingType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssUnrecognizeProperties n, C ctx) throws Exception {
        if (n.getGssUnrecognizeProperty() != null) {
            for (GssUnrecognizeProperty item : n.getGssUnrecognizeProperty()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(GssUnrecognizeProperty n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(GssVendor n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(IncludeInputFilter n, C ctx) throws Exception {
        if (n.getPattern() != null) {
            for (InputFilterPattern item : n.getPattern()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(Info n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(InputFilterPattern n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(InputFilterType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsDefine n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(JsDefines n, C ctx) throws Exception {
        if (n.getJsDefine() != null) {
            for (JsDefine item : n.getJsDefine()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(JsDefineType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsDoc n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsDocs n, C ctx) throws Exception {
        if (n.getJsDoc() != null) {
            for (JsDoc item : n.getJsDoc()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(JsFunctionMap n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsPropertyMap n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsRenaming n, C ctx) throws Exception {
        if (n.getVariableMap() != null) {
            n.getVariableMap().accept(this, ctx);
        }
        if (n.getFunctionMap() != null) {
            n.getFunctionMap().accept(this, ctx);
        }
        if (n.getPropertyMap() != null) {
            n.getPropertyMap().accept(this, ctx);
        }
        if (n.getVariablePolice() != null) {
            n.getVariablePolice().accept(this, ctx);
        }
        if (n.getFunctionPolice() != null) {
            n.getFunctionPolice().accept(this, ctx);
        }
        if (n.getPropertyPolice() != null) {
            n.getPropertyPolice().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(JsRenamingFunctionPolice n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsRenamingPropertyPolice n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsRenamingVariablePolice n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsSourceMapFormat n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(JsVariableMap n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(LangType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Language n, C ctx) throws Exception {
        if (n.getInput() != null) {
            n.getInput().accept(this, ctx);
        }
        if (n.getOutput() != null) {
            n.getOutput().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Optimization n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Optimizations n, C ctx) throws Exception {
        if (n.getOptimization() != null) {
            for (Optimization item : n.getOptimization()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(OptimizationType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Output n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(SeverityType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Source n, C ctx) throws Exception {
        if (n.getExcluded() != null) {
            n.getExcluded().accept(this, ctx);
        }
        if (n.getIncluded() != null) {
            n.getIncluded().accept(this, ctx);
        }
        if (n.getEntryKind() != null) {
            n.getEntryKind().accept(this, ctx);
        }
        if (n.getEntityKind() != null) {
            n.getEntityKind().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(SourceEntity n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(SourceEntry n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(SoyCodeStyle n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(SoyCssSchemeType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(SoyLocale n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(SoyLocales n, C ctx) throws Exception {
        if (n.getSoyLocale() != null) {
            for (SoyLocale item : n.getSoyLocale()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(Warning n, C ctx) throws Exception {
        if (n.getSeverity() != null) {
            n.getSeverity().accept(this, ctx);
        }
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Warnings n, C ctx) throws Exception {
        if (n.getWarning() != null) {
            for (Warning item : n.getWarning()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(WarningType n, C ctx) throws Exception {
        return null;
    }

    public GenericVisitorAdapter() {
        super();
    }

}
