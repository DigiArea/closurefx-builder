package com.digiarea.closurefx.build.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closurefx.IConstants;

public class GSSBuildpathContainerResolver {

	private Buildpath container;
	private LinkedHashSet<File> externs = new LinkedHashSet<File>();
	private LinkedHashSet<File> sources = new LinkedHashSet<File>();
	private IPathResolver resolver;

	public GSSBuildpathContainerResolver(Buildpath container,
			IPathResolver resolver) {
		this.container = container;
		this.resolver = resolver;
	}

	public LinkedHashSet<File> getSources() {
		return sources;
	}

	public LinkedHashSet<File> getExterns() {
		return externs;
	}

	public void resolve() {
		for (Source entry : container.getSource()) {
			File file = new File(
					resolver.toRealPath(entry.getPath().toString()));
			if (!entry.isExtern()) {
				sources.addAll(readEntry(file, SourceEntity.GSS));
			} else {
				externs.addAll(readEntry(file, SourceEntity.GSS));
			}
		}
	}

	private List<File> readEntry(File file, SourceEntity kind) {
		List<File> files = new ArrayList<File>();
		if (file.isDirectory()) {
			String entries[] = file.list();
			if (entries != null) {
				for (String entry : entries) {
					files.addAll(readEntry(new File(file, entry), kind));
				}
			}
		} else {
			if (kind == SourceEntity.GSS) {
				if (file.getName().endsWith(IConstants.EXTENSION_CSS)
						|| file.getName().endsWith(IConstants.EXTENSION_GSS)) {
					files.add(file);
				}
			}
		}
		return files;
	}

}
