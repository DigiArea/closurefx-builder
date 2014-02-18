package com.digiarea.closurefx.build.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Source;
import com.digiarea.closurefx.IConstants;
import com.google.javascript.jscomp.deps.DependencyInfo;
import com.google.javascript.jscomp.deps.JsFileParser;
import com.google.javascript.jscomp.deps.SortedDependencies;
import com.google.javascript.jscomp.deps.SortedDependencies.CircularDependencyException;

public class JSBuildpathContainerResolver {

	private Buildpath container;
	private List<File> externs = new ArrayList<File>();
	private List<File> sources = new ArrayList<File>();
	private IPathResolver pathResolver;
	private SortedDependencies<DependencyInfo> deps;
	private List<DependencyInfo> sourceDeps = new ArrayList<DependencyInfo>();
	private List<DependencyInfo> simpleDeps = new ArrayList<DependencyInfo>();
	private List<DependencyInfo> closureDeps = new ArrayList<DependencyInfo>();

	public JSBuildpathContainerResolver(Buildpath container,
			IPathResolver pathResolver) {
		this.container = container;
		this.pathResolver = pathResolver;
	}

	public List<File> getSources() {
		return sources;
	}

	public List<File> getExterns() {
		return externs;
	}

	public void getDependencies() throws CircularDependencyException {
		readEntries();
		deps = new SortedDependencies<DependencyInfo>(
				new ArrayList<DependencyInfo>(closureDeps));
	}

	private DependencyInfo getDependency(File file) {
		JsFileParser parser = new JsFileParser(null);
		try {
			return parser.parseFile(file.toString(), null);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void resolve() {
		for (DependencyInfo dependencyInfo : simpleDeps) {
			sources.add(new File(dependencyInfo.getName()));
		}
		List<DependencyInfo> infos = deps.getDependenciesOf(sourceDeps, false);
		for (DependencyInfo dependencyInfo : infos) {
			sources.add(new File(dependencyInfo.getName()));
		}
	}

	private void readEntries() {
		// loop reverse for order
		for (int i = container.getSource().size() - 1; i >= 0; i--) {
			Source source = container.getSource().get(i);
			File file = new File(pathResolver.toRealPath(source.getPath()
					.toString()));
			readEntry(source, file);
		}
	}

	private List<File> readEntry(Source source, File file) {
		List<File> files = new ArrayList<File>();
		if (file.isDirectory()) {
			String entries[] = file.list();
			if (entries != null) {
				for (String entry : entries) {
					files.addAll(readEntry(source, new File(file, entry)));
				}
			}
		} else {
			if (file.getName().endsWith(IConstants.EXTENSION_JS)) {
				DependencyInfo info = getDependency(file);
				if (source.isExtern()) {
					if (source.isIncludeSimple()
							&& info.getProvides().isEmpty()) {
						externs.add(file);
					} else if (source.isIncludeClosure()) {
						externs.add(file);
					}
				} else {
					switch (source.getEntryKind()) {
					case CLOSURE:
						// only Closure Files
						if (!info.getProvides().isEmpty()) {
							closureDeps.add(info);
						} else {
							if (info.getName()
									.endsWith(IConstants.CLOSURE_BASE)) {
								simpleDeps.add(info);
							}
						}
						break;
					case FILE:
					case CONTAINER:
					case LIBRARY:
					case PROJECT:
					case VARIABLE:
						if (source.isIncludeSimple()
								&& info.getProvides().isEmpty()) {
							simpleDeps.add(info);
						} else if (source.isIncludeClosure()) {
							closureDeps.add(info);
						}
						break;
					case SOURCE:
						sourceDeps.add(info);
						closureDeps.add(info);
						break;
					}
				}
			}
		}
		return files;
	}
}
