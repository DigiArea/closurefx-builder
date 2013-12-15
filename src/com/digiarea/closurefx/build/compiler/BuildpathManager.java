package com.digiarea.closurefx.build.compiler;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Closure;
import com.digiarea.closurefx.ClosureSerializerFactory;
import com.digiarea.closurefx.IClosureSerializer;
import com.digiarea.closurefx.build.Closurer;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.css.JobDescriptionBuilder;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.template.soy.jssrc.SoyJsSrcOptions;

public class BuildpathManager {

	private IClosureSerializer serializer = ClosureSerializerFactory
			.getSerializer();

	/**
	 * Saves the last date of the modifications of the build files. We use it,
	 * to reload configurations cache.
	 */
	private HashMap<String, Long> reads = new HashMap<String, Long>();

	private LoadingCache<String, Closure> closures = CacheBuilder.newBuilder()
			.maximumSize(50).build(new CacheLoader<String, Closure>() {
				public Closure load(String path) throws Exception {
					return serializer.read(path.toString());
				}
			});

	public Closure getClosure(String path) {
		try {
			File file = new File(path);
			// reload configuration if it is more new then we have
			if ((reads.get(path) != null)
					&& (file.lastModified() != reads.get(path))) {
				closures.refresh(path);
			}
			reads.put(path, file.lastModified());
			return closures.get(path);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CompilerOptions getJSCOptions(Closure closure,
			IPathResolver pathResolver) {
		try {
			Closurer closurer = new Closurer(pathResolver);
			closure.getClosureJs().accept(closurer, null);
			return closurer.getJsOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SoyJsSrcOptions getSOYOptions(Closure closure,
			IPathResolver pathResolver) {
		try {
			Closurer closurer = new Closurer(pathResolver);
			closure.getClosureSoy().accept(closurer, null);
			return closurer.getSoyOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JobDescriptionBuilder getGSSOptions(Closure closure,
			IPathResolver pathResolver) {
		try {
			Closurer closurer = new Closurer(pathResolver);
			closure.getClosureGss().accept(closurer, null);
			return closurer.getGssOptions();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
