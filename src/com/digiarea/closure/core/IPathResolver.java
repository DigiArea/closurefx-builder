package com.digiarea.closure.core;

public interface IPathResolver {

	/**
	 * @param path
	 * @return absolute path
	 */
	public String toRealPath(String path);

	public String toRelativePath(String path);

	public void setBasePath(String path);
	
	public String getBasePath();

}
