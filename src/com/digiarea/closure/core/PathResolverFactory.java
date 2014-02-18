package com.digiarea.closure.core;

import java.io.File;

public class PathResolverFactory {

	public static IPathResolver getResolver(String basePath) {
		return new ClosureFXResolver(basePath);
	}

	public static IPathResolver getResolver(File file) {
		if (file != null) {
			return getResolver(file.getAbsolutePath());
		}
		return getResolver("");
	}

}
