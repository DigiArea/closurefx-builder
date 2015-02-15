package com.digiarea.closurefx.utils;

import com.digiarea.closure.model.JsSourceMapFormat;
import com.google.javascript.jscomp.SourceMap;

public class ClosurerUtils {
	
	public static boolean isAdvancedMode() {
		return false;
	}

	public static SourceMap.Format toSourceMapFormat(JsSourceMapFormat n) {
		switch (n) {
		case V_3:
			return SourceMap.Format.V3;
		default:
			return SourceMap.Format.DEFAULT;
		}
	}

}
