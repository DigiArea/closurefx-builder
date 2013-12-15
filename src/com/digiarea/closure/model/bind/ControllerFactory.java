package com.digiarea.closure.model.bind;

import java.util.ResourceBundle;

import javafx.util.Callback;

import com.digiarea.closure.model.controller.ClosureEditorController;
import com.digiarea.closure.model.controller.GSSConsoleSectionController;
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
import com.digiarea.closure.model.controller.JSConsoleSectionController;
import com.digiarea.closure.model.controller.JSDefinesSectionController;
import com.digiarea.closure.model.controller.JSDocsSectionController;
import com.digiarea.closure.model.controller.JSExportsSectionController;
import com.digiarea.closure.model.controller.JSFormattingSectionController;
import com.digiarea.closure.model.controller.JSHelperSectionController;
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
import com.digiarea.closure.model.controller.SOYConsoleSectionController;
import com.digiarea.closure.model.controller.SOYInfoSectionController;
import com.digiarea.closure.model.controller.SOYLibrariesSectionController;
import com.digiarea.closure.model.controller.SOYLocalizationSectionController;
import com.digiarea.closure.model.controller.SOYOptionsSectionController;
import com.digiarea.closure.model.controller.SOYOrderSectionController;
import com.digiarea.closure.model.controller.SOYOutputSectionController;
import com.digiarea.closure.model.controller.SOYPageController;
import com.digiarea.closure.model.controller.SOYSourceSectionController;
import com.digiarea.closure.model.controller.SOYStyleSectionController;

public class ControllerFactory implements Callback<Class<?>, Object> {

    @Override
    public Object call(Class<?> clazz) {
        if (clazz == SOYStyleSectionController.class) {
            if (sOYStyleSectionController == null) {
                sOYStyleSectionController = new SOYStyleSectionController(modelFacade, bundle);
            }
            return sOYStyleSectionController;
        } else if (clazz == SOYSourceSectionController.class) {
            if (sOYSourceSectionController == null) {
                sOYSourceSectionController = new SOYSourceSectionController(modelFacade, bundle);
            }
            return sOYSourceSectionController;
        } else if (clazz == SOYPageController.class) {
            if (sOYPageController == null) {
                sOYPageController = new SOYPageController(modelFacade, bundle);
            }
            return sOYPageController;
        } else if (clazz == SOYOutputSectionController.class) {
            if (sOYOutputSectionController == null) {
                sOYOutputSectionController = new SOYOutputSectionController(modelFacade, bundle);
            }
            return sOYOutputSectionController;
        } else if (clazz == SOYOrderSectionController.class) {
            if (sOYOrderSectionController == null) {
                sOYOrderSectionController = new SOYOrderSectionController(modelFacade, bundle);
            }
            return sOYOrderSectionController;
        } else if (clazz == SOYOptionsSectionController.class) {
            if (sOYOptionsSectionController == null) {
                sOYOptionsSectionController = new SOYOptionsSectionController(modelFacade, bundle);
            }
            return sOYOptionsSectionController;
        } else if (clazz == SOYLocalizationSectionController.class) {
            if (sOYLocalizationSectionController == null) {
                sOYLocalizationSectionController = new SOYLocalizationSectionController(modelFacade, bundle);
            }
            return sOYLocalizationSectionController;
        } else if (clazz == SOYLibrariesSectionController.class) {
            if (sOYLibrariesSectionController == null) {
                sOYLibrariesSectionController = new SOYLibrariesSectionController(modelFacade, bundle);
            }
            return sOYLibrariesSectionController;
        } else if (clazz == SOYInfoSectionController.class) {
            if (sOYInfoSectionController == null) {
                sOYInfoSectionController = new SOYInfoSectionController(modelFacade, bundle);
            }
            return sOYInfoSectionController;
        } else if (clazz == SOYConsoleSectionController.class) {
            if (sOYConsoleSectionController == null) {
                sOYConsoleSectionController = new SOYConsoleSectionController(modelFacade, bundle);
            }
            return sOYConsoleSectionController;
        } else if (clazz == JSWarningsSectionController.class) {
            if (jSWarningsSectionController == null) {
                jSWarningsSectionController = new JSWarningsSectionController(modelFacade, bundle);
            }
            return jSWarningsSectionController;
        } else if (clazz == JSTranslationSectionController.class) {
            if (jSTranslationSectionController == null) {
                jSTranslationSectionController = new JSTranslationSectionController(modelFacade, bundle);
            }
            return jSTranslationSectionController;
        } else if (clazz == JSSourceSectionController.class) {
            if (jSSourceSectionController == null) {
                jSSourceSectionController = new JSSourceSectionController(modelFacade, bundle);
            }
            return jSSourceSectionController;
        } else if (clazz == JSSourceMapSectionController.class) {
            if (jSSourceMapSectionController == null) {
                jSSourceMapSectionController = new JSSourceMapSectionController(modelFacade, bundle);
            }
            return jSSourceMapSectionController;
        } else if (clazz == JSRenamingSectionController.class) {
            if (jSRenamingSectionController == null) {
                jSRenamingSectionController = new JSRenamingSectionController(modelFacade, bundle);
            }
            return jSRenamingSectionController;
        } else if (clazz == JSPageController.class) {
            if (jSPageController == null) {
                jSPageController = new JSPageController(modelFacade, bundle);
            }
            return jSPageController;
        } else if (clazz == JSOutputSectionController.class) {
            if (jSOutputSectionController == null) {
                jSOutputSectionController = new JSOutputSectionController(modelFacade, bundle);
            }
            return jSOutputSectionController;
        } else if (clazz == JSOrderSectionController.class) {
            if (jSOrderSectionController == null) {
                jSOrderSectionController = new JSOrderSectionController(modelFacade, bundle);
            }
            return jSOrderSectionController;
        } else if (clazz == JSLibrariesSectionController.class) {
            if (jSLibrariesSectionController == null) {
                jSLibrariesSectionController = new JSLibrariesSectionController(modelFacade, bundle);
            }
            return jSLibrariesSectionController;
        } else if (clazz == JSLanguageSectionController.class) {
            if (jSLanguageSectionController == null) {
                jSLanguageSectionController = new JSLanguageSectionController(modelFacade, bundle);
            }
            return jSLanguageSectionController;
        } else if (clazz == JSInfoSectionController.class) {
            if (jSInfoSectionController == null) {
                jSInfoSectionController = new JSInfoSectionController(modelFacade, bundle);
            }
            return jSInfoSectionController;
        } else if (clazz == JSHelperSectionController.class) {
            if (jSHelperSectionController == null) {
                jSHelperSectionController = new JSHelperSectionController(modelFacade, bundle);
            }
            return jSHelperSectionController;
        } else if (clazz == JSFormattingSectionController.class) {
            if (jSFormattingSectionController == null) {
                jSFormattingSectionController = new JSFormattingSectionController(modelFacade, bundle);
            }
            return jSFormattingSectionController;
        } else if (clazz == JSExportsSectionController.class) {
            if (jSExportsSectionController == null) {
                jSExportsSectionController = new JSExportsSectionController(modelFacade, bundle);
            }
            return jSExportsSectionController;
        } else if (clazz == JSDocsSectionController.class) {
            if (jSDocsSectionController == null) {
                jSDocsSectionController = new JSDocsSectionController(modelFacade, bundle);
            }
            return jSDocsSectionController;
        } else if (clazz == JSDefinesSectionController.class) {
            if (jSDefinesSectionController == null) {
                jSDefinesSectionController = new JSDefinesSectionController(modelFacade, bundle);
            }
            return jSDefinesSectionController;
        } else if (clazz == JSConsoleSectionController.class) {
            if (jSConsoleSectionController == null) {
                jSConsoleSectionController = new JSConsoleSectionController(modelFacade, bundle);
            }
            return jSConsoleSectionController;
        } else if (clazz == JSCheckSectionController.class) {
            if (jSCheckSectionController == null) {
                jSCheckSectionController = new JSCheckSectionController(modelFacade, bundle);
            }
            return jSCheckSectionController;
        } else if (clazz == GSSVendorSectionController.class) {
            if (gSSVendorSectionController == null) {
                gSSVendorSectionController = new GSSVendorSectionController(modelFacade, bundle);
            }
            return gSSVendorSectionController;
        } else if (clazz == GSSSourceSectionController.class) {
            if (gSSSourceSectionController == null) {
                gSSSourceSectionController = new GSSSourceSectionController(modelFacade, bundle);
            }
            return gSSSourceSectionController;
        } else if (clazz == GSSRenamingSectionController.class) {
            if (gSSRenamingSectionController == null) {
                gSSRenamingSectionController = new GSSRenamingSectionController(modelFacade, bundle);
            }
            return gSSRenamingSectionController;
        } else if (clazz == GSSPageController.class) {
            if (gSSPageController == null) {
                gSSPageController = new GSSPageController(modelFacade, bundle);
            }
            return gSSPageController;
        } else if (clazz == GSSOutputSectionController.class) {
            if (gSSOutputSectionController == null) {
                gSSOutputSectionController = new GSSOutputSectionController(modelFacade, bundle);
            }
            return gSSOutputSectionController;
        } else if (clazz == GSSOrderSectionController.class) {
            if (gSSOrderSectionController == null) {
                gSSOrderSectionController = new GSSOrderSectionController(modelFacade, bundle);
            }
            return gSSOrderSectionController;
        } else if (clazz == GSSLintingSectionController.class) {
            if (gSSLintingSectionController == null) {
                gSSLintingSectionController = new GSSLintingSectionController(modelFacade, bundle);
            }
            return gSSLintingSectionController;
        } else if (clazz == GSSLibrariesSectionController.class) {
            if (gSSLibrariesSectionController == null) {
                gSSLibrariesSectionController = new GSSLibrariesSectionController(modelFacade, bundle);
            }
            return gSSLibrariesSectionController;
        } else if (clazz == GSSInfoSectionController.class) {
            if (gSSInfoSectionController == null) {
                gSSInfoSectionController = new GSSInfoSectionController(modelFacade, bundle);
            }
            return gSSInfoSectionController;
        } else if (clazz == GSSFormattingSectionController.class) {
            if (gSSFormattingSectionController == null) {
                gSSFormattingSectionController = new GSSFormattingSectionController(modelFacade, bundle);
            }
            return gSSFormattingSectionController;
        } else if (clazz == GSSDefinesSectionController.class) {
            if (gSSDefinesSectionController == null) {
                gSSDefinesSectionController = new GSSDefinesSectionController(modelFacade, bundle);
            }
            return gSSDefinesSectionController;
        } else if (clazz == GSSCopySectionController.class) {
            if (gSSCopySectionController == null) {
                gSSCopySectionController = new GSSCopySectionController(modelFacade, bundle);
            }
            return gSSCopySectionController;
        } else if (clazz == GSSConsoleSectionController.class) {
            if (gSSConsoleSectionController == null) {
                gSSConsoleSectionController = new GSSConsoleSectionController(modelFacade, bundle);
            }
            return gSSConsoleSectionController;
        } else if (clazz == ClosureEditorController.class) {
            if (closureEditorController == null) {
                closureEditorController = new ClosureEditorController(modelFacade, bundle);
            }
            return closureEditorController;
        } else {
            return null;
        }
    }

    public ControllerFactory(ModelFacade modelFacade, ResourceBundle bundle) {
        modelFacade.setFactory(this);
        this.modelFacade = modelFacade;
        this.bundle = bundle;
    }

    private ModelFacade modelFacade = null;

    private ResourceBundle bundle = null;

    private ClosureEditorController closureEditorController = null;

    private GSSConsoleSectionController gSSConsoleSectionController = null;

    private GSSCopySectionController gSSCopySectionController = null;

    private GSSDefinesSectionController gSSDefinesSectionController = null;

    private GSSFormattingSectionController gSSFormattingSectionController = null;

    private GSSInfoSectionController gSSInfoSectionController = null;

    private GSSLibrariesSectionController gSSLibrariesSectionController = null;

    private GSSLintingSectionController gSSLintingSectionController = null;

    private GSSOrderSectionController gSSOrderSectionController = null;

    private GSSOutputSectionController gSSOutputSectionController = null;

    private GSSPageController gSSPageController = null;

    private GSSRenamingSectionController gSSRenamingSectionController = null;

    private GSSSourceSectionController gSSSourceSectionController = null;

    private GSSVendorSectionController gSSVendorSectionController = null;

    private JSCheckSectionController jSCheckSectionController = null;

    private JSConsoleSectionController jSConsoleSectionController = null;

    private JSDefinesSectionController jSDefinesSectionController = null;

    private JSDocsSectionController jSDocsSectionController = null;

    private JSExportsSectionController jSExportsSectionController = null;

    private JSFormattingSectionController jSFormattingSectionController = null;

    private JSHelperSectionController jSHelperSectionController = null;

    private JSInfoSectionController jSInfoSectionController = null;

    private JSLanguageSectionController jSLanguageSectionController = null;

    private JSLibrariesSectionController jSLibrariesSectionController = null;

    private JSOrderSectionController jSOrderSectionController = null;

    private JSOutputSectionController jSOutputSectionController = null;

    private JSPageController jSPageController = null;

    private JSRenamingSectionController jSRenamingSectionController = null;

    private JSSourceMapSectionController jSSourceMapSectionController = null;

    private JSSourceSectionController jSSourceSectionController = null;

    private JSTranslationSectionController jSTranslationSectionController = null;

    private JSWarningsSectionController jSWarningsSectionController = null;

    private SOYConsoleSectionController sOYConsoleSectionController = null;

    private SOYInfoSectionController sOYInfoSectionController = null;

    private SOYLibrariesSectionController sOYLibrariesSectionController = null;

    private SOYLocalizationSectionController sOYLocalizationSectionController = null;

    private SOYOptionsSectionController sOYOptionsSectionController = null;

    private SOYOrderSectionController sOYOrderSectionController = null;

    private SOYOutputSectionController sOYOutputSectionController = null;

    private SOYPageController sOYPageController = null;

    private SOYSourceSectionController sOYSourceSectionController = null;

    private SOYStyleSectionController sOYStyleSectionController = null;

}
