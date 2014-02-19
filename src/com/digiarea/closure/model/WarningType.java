package com.digiarea.closure.model;

import java.io.IOException;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public enum WarningType {

    ES3, 
    CHECK_EVENTFUL_OBJECT_DISPOSAL, 
    UNNECESSARY_CASTS, 
    ACCESS_CONTROLS, 
    AMBIGUOUS_FUNCTION_DECL, 
    DEBUGGER_STATEMENT_PRESENT, 
    CHECK_REGEXP, 
    CHECK_TYPES, 
    CHECK_VARS, 
    CONST, 
    CONSTANT_PROPERTY, 
    DEPRECATED, 
    DUPLICATE_VARS, 
    ES_5_STRICT, 
    EXTERNS_VALIDATION, 
    FILEOVERVIEW_JSDOC, 
    GLOBAL_THIS, 
    INTERNET_EXPLORER_CHECKS, 
    INVALID_CASTS, 
    MISSING_PROPERTIES, 
    NON_STANDARD_JS_DOCS, 
    STRICT_MODULE_DEP_CHECK, 
    SUSPICIOUS_CODE, 
    UNDEFINED_NAMES, 
    UNDEFINED_VARS, 
    UNKNOWN_DEFINES, 
    CHECK_USELESS_CODE, 
    VISIBILITY, 
    AGGRESSIVE_VAR_CHECK, 
    REPORT_MISSING_OVERRIDE, 
    REPORT_UNKNOWN_TYPES, 
    CHECK_REQUIRES, 
    CHECK_PROVIDES, 
    CHECK_GLOBAL_NAMES_LEVEL, 
    BROKEN_REQUIRES_LEVEL, 
    CHECK_GLOBAL_THIS_LEVEL, 
    CHECK_UNREACHABLE_CODE, 
    CHECK_MISSING_RETURN, 
    DUPLICATE_MESSAGES, 
    VIOLATED_MODULE_DEP, 
    TWEAKS, 
    CHECK_STRUCT_DICT_INHERITENCE, 
    TYPE_INVALIDATION, 
    MISPLACED_TYPE_ANNOTATION;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfWarningType() {
        int size = 0;
        size += ZippyBuffer.sizeOfRawVarInt(ordinal());
        size += 1;
        return size;
    }

    public final void writeWarningType(final ZippyBuffer writer) throws IOException {
        writer.writeRawVarInt(ordinal());
        writer.writeRawVarInt(0);
    }

    public static WarningType readWarningType(final ZippyBuffer reader) throws IOException {
        final WarningType packet = WarningType.values()[reader.readRawVarInt()];
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
            }
        }
        return packet;
    }

}
