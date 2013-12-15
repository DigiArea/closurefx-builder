package com.digiarea.closure.core;

import java.io.File;
import java.io.IOException;

import com.digiarea.closure.preferences.model.ClosureLibraries;
import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.Variables;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;

public class ClosureFXResolver implements IPathResolver {

	/**
	 * path to .closure file.
	 * */
	private String basePath;

	public ClosureFXResolver(String basePath) {
		this.basePath = basePath;
	}

	@Override
	public void setBasePath(String path) {
		this.basePath = path;
	}

	public String getBasePath() {
		return basePath;
	}
	
	@Override
	public String toRealPath(String input) {
		String path = input;
		if (path != null) {
			// resolve variables if any
			Variables variables = new PreferencesSerializer().readVariables();
			if (variables != null && !variables.getVariables().isEmpty()) {
				for (Variable var : variables.getVariables()) {
					if (path.contains(var.getPlaceholder())) {
						path = path
								.replace(var.getPlaceholder(), var.getPath());
					}
				}
			}
			// resolve Closure Library if any
			ClosureLibraries closureLibraries = new PreferencesSerializer()
					.readLibraries();
			if (closureLibraries != null
					&& !closureLibraries.getLibraries().isEmpty()) {
				for (ClosureLibrary var : closureLibraries.getLibraries()) {
					if (path.contains(var.getName())) {
						path = path.replace(var.getName(), var.getPath());
					}
				}
			}
			if (basePath != null && !basePath.isEmpty()) {
				File a = new File(basePath).getParentFile();
				File b = new File(a, path);
				if (!new Path(path).isAbsolute()) {
					try {
						path = b.getCanonicalPath();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}
		}
		return path;
	}

	@Override
	public String toRelativePath(String input) {
		if (basePath != null && input != null) {
			return new File(basePath).getParentFile().toURI()
					.relativize(new File(input).toURI()).getPath();
		}
		return input;
	}

}
