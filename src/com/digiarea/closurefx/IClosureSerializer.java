package com.digiarea.closurefx;

import java.io.InputStream;

import com.digiarea.closure.model.Closure;

public interface IClosureSerializer {

	public Closure read(String path) throws Exception;

	public Closure read(InputStream stream) throws Exception;

	public void write(Closure closure, String path) throws Exception;

}
