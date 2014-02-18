package com.digiarea.closure.model;

import java.io.IOException;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public enum OptimizationType {

    FOLD_CONSTANTS, 
    COALESCE_VARIABLE_NAMES, 
    DEAD_ASSIGNMENT_ELIMINATION, 
    INLINE_CONSTANT_VAR, 
    INLINE_FUNCTION, 
    INLINE_LOCAL_FUNCTION, 
    ASSUME_STRICT_THIS, 
    ASSUME_CLOSURES_ONLY_CAPTURE_PREFERENCES, 
    INLINE_PROPERTIES, 
    CROSS_MODULE_CODE_MOTION, 
    CROSS_MODULE_METHOD_MOTION, 
    INLINE_GETTERS, 
    INLINE_VARIABLES, 
    INLINE_LOCAL_VARIABLES, 
    SMART_NAME_REMOVAL, 
    REMOVE_DEAD_CODE, 
    EXTRACT_PROTOTYPE_MEMBER_DECLARATION, 
    REMOVE_UNUSED_PROTOTYPE_PROPERTIES, 
    REMOVE_UNUSED_PROTOTYPE_PROPERTIES_IN_EXTERNS, 
    REMOVE_UNUSED_CLASS_PROPERTIES, 
    REMOVE_UNUSED_VARS, 
    REMOVE_UNUSED_LOCAL_VARS, 
    ALIAS_EXTERNALS, 
    COLLAPSE_VARIABLE_DECLARATIONS, 
    GROUP_VARIABLE_DECLARATIONS, 
    COLLAPSE_ANONYMOUS_FUNCTIONS, 
    ALIAS_ALL_STRINGS, 
    OUTPUT_JS_STRING_USAGE, 
    CONVERT_TO_DOTTED_PROPERTIES, 
    REWRITE_FUNCTIONS_EXPRESSIONS, 
    OPTIMIZE_PARAMETERS, 
    OPTIMIZE_RETURNS, 
    ALIAS_KEYWORDS, 
    COLLAPSE_PROPERTIES, 
    COLLAPSE_PROPERTIES_ON_EXTERN_TYPES, 
    COLLAPSE_OBJECT_LITERALS, 
    OPTIMIZE_CALLS, 
    OPTIMIZE_ARGUMENTS_ARRAY;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfOptimizationType() {
        int size = 0;
        size += ZippyBuffer.sizeOfRawVarInt(ordinal());
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeOptimizationType(final ZippyBuffer writer) throws IOException {
        writer.writeRawVarInt(ordinal());
        writer.writeRawVarInt(0);
    }

    public static OptimizationType readOptimizationType(final ZippyBuffer reader) throws IOException {
        final OptimizationType packet = OptimizationType.values()[reader.readRawVarInt()];
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
            }
        }
        return packet;
    }

}
