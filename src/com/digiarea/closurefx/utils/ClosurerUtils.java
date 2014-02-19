package com.digiarea.closurefx.utils;

import com.digiarea.closure.model.JsSourceMapFormat;
import com.google.javascript.jscomp.SourceMap;

public class ClosurerUtils {

	public static SourceMap.Format toSourceMapFormat(JsSourceMapFormat n) {
		switch (n) {
		case V_1:
			return SourceMap.Format.V1;
		case V_2:
			return SourceMap.Format.V2;
		case V_3:
			return SourceMap.Format.V3;
		default:
			return SourceMap.Format.DEFAULT;
		}
	}

}
