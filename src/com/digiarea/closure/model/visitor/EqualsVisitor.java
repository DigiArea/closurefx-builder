package com.digiarea.closure.model.visitor;

import java.util.List;

import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.CheckType;
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
import com.digiarea.closure.model.InputFilterPattern;
import com.digiarea.closure.model.InputFilterType;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefineType;
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
import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.Optimization;
import com.digiarea.closure.model.OptimizationType;
import com.digiarea.closure.model.Optimizations;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.SoyCodeStyle;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.SoyLocales;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.WarningType;
import com.digiarea.closure.model.Warnings;

@SuppressWarnings("unchecked")
public class EqualsVisitor implements GenericVisitor<Boolean, Node> {

    private static final EqualsVisitor SINGLETON = new EqualsVisitor();

    public static boolean equals(Node n1, Node n2) throws Exception {
        return SINGLETON.nodeEquals(n1, n2);
    }

    protected <T extends Node> boolean nodeEquals(T n1, T n2) throws Exception {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        if (n1.getClass() != n2.getClass()) {
            return false;
        }
        return n1.accept(this, n2).booleanValue();
    }

    protected <T extends Node> boolean nodesEquals(List<T> nodes1, List<T> nodes2) throws Exception {
        if (nodes1 == null) {
            if (nodes2 == null) {
                return true;
            }
            return false;
        } else if (nodes2 == null) {
            return false;
        }
        if (nodes1.size() != nodes2.size()) {
            return false;
        }
        for (int i = 0; i < nodes1.size(); i++) {
            if (!nodeEquals(nodes1.get(i), nodes2.get(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean objEquals(Object n1, Object n2) {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        return n1.equals(n2);
    }

    @Override
    public Boolean visit(GssRenamingType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(LangType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(GssExcludedClasses n, Node ctx) throws Exception {
        GssExcludedClasses x = (GssExcludedClasses) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssOutputOrientation n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Warning n, Node ctx) throws Exception {
        Warning x = (Warning) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssAtRule n, Node ctx) throws Exception {
        GssAtRule x = (GssAtRule) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssUnrecognizeProperties n, Node ctx) throws Exception {
        GssUnrecognizeProperties x = (GssUnrecognizeProperties) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(InputFilterType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SourceEntry n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsDefineType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Optimizations n, Node ctx) throws Exception {
        Optimizations x = (Optimizations) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SoyCodeStyle n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SourceEntity n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(ClosureJs n, Node ctx) throws Exception {
        ClosureJs x = (ClosureJs) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssVendor n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsFunctionMap n, Node ctx) throws Exception {
        JsFunctionMap x = (JsFunctionMap) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsDoc n, Node ctx) throws Exception {
        JsDoc x = (JsDoc) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ClosureGss n, Node ctx) throws Exception {
        ClosureGss x = (ClosureGss) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Check n, Node ctx) throws Exception {
        Check x = (Check) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsSourceMapFormat n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsDocs n, Node ctx) throws Exception {
        JsDocs x = (JsDocs) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssDefine n, Node ctx) throws Exception {
        GssDefine x = (GssDefine) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssNonStandardFunctions n, Node ctx) throws Exception {
        GssNonStandardFunctions x = (GssNonStandardFunctions) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ClosureSoy n, Node ctx) throws Exception {
        ClosureSoy x = (ClosureSoy) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssOutputFormat n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SoyLocale n, Node ctx) throws Exception {
        SoyLocale x = (SoyLocale) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ExcludeInputFilter n, Node ctx) throws Exception {
        ExcludeInputFilter x = (ExcludeInputFilter) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsPropertyMap n, Node ctx) throws Exception {
        JsPropertyMap x = (JsPropertyMap) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssInputOrientation n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(GssOutputRenamingMapFormat n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Checks n, Node ctx) throws Exception {
        Checks x = (Checks) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SoyCssSchemeType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(IncludeInputFilter n, Node ctx) throws Exception {
        IncludeInputFilter x = (IncludeInputFilter) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssDefines n, Node ctx) throws Exception {
        GssDefines x = (GssDefines) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Optimization n, Node ctx) throws Exception {
        Optimization x = (Optimization) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Buildpath n, Node ctx) throws Exception {
        Buildpath x = (Buildpath) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenamingVariablePolice n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(InputFilterPattern n, Node ctx) throws Exception {
        InputFilterPattern x = (InputFilterPattern) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(WarningType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Warnings n, Node ctx) throws Exception {
        Warnings x = (Warnings) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenaming n, Node ctx) throws Exception {
        JsRenaming x = (JsRenaming) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(OptimizationType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SoyLocales n, Node ctx) throws Exception {
        SoyLocales x = (SoyLocales) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssAtRules n, Node ctx) throws Exception {
        GssAtRules x = (GssAtRules) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenamingPropertyPolice n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsDefines n, Node ctx) throws Exception {
        JsDefines x = (JsDefines) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SeverityType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(GssUnrecognizeProperty n, Node ctx) throws Exception {
        GssUnrecognizeProperty x = (GssUnrecognizeProperty) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Language n, Node ctx) throws Exception {
        Language x = (Language) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssOptimizationLevel n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(CheckType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Source n, Node ctx) throws Exception {
        Source x = (Source) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Info n, Node ctx) throws Exception {
        Info x = (Info) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssNonStandardFunction n, Node ctx) throws Exception {
        GssNonStandardFunction x = (GssNonStandardFunction) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Closure n, Node ctx) throws Exception {
        Closure x = (Closure) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssExcludedClass n, Node ctx) throws Exception {
        GssExcludedClass x = (GssExcludedClass) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsDefine n, Node ctx) throws Exception {
        JsDefine x = (JsDefine) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenamingFunctionPolice n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Output n, Node ctx) throws Exception {
        Output x = (Output) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsVariableMap n, Node ctx) throws Exception {
        JsVariableMap x = (JsVariableMap) ctx;
        if (!nodeEquals(n.getParent(), x.getParent())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    public EqualsVisitor() {
        super();
    }

}
