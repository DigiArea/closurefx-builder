package com.digiarea.closure.core;

public interface IPath extends Cloneable {

	/**
	 * Path separator character constant "/" used in paths.
	 */
	public static final char SEPARATOR = '/';

	/**
	 * Device separator character constant ":" used in paths.
	 */
	public static final char DEVICE_SEPARATOR = ':';

	public IPath addFileExtension(String extension);

	public IPath addTrailingSeparator();

	public IPath append(String path);

	public IPath append(IPath path);

	public Object clone();

	public boolean equals(Object obj);

	public String getDevice();

	public String getFileExtension();

	public boolean hasTrailingSeparator();

	public boolean isAbsolute();

	public boolean isEmpty();

	public boolean isPrefixOf(IPath anotherPath);

	public boolean isRoot();

	public boolean isUNC();

	public boolean isValidPath(String path);

	public boolean isValidSegment(String segment);

	public String lastSegment();

	public IPath makeAbsolute();

	public IPath makeRelative();

	public IPath makeRelativeTo(IPath base);

	public IPath makeUNC(boolean toUNC);

	public int matchingFirstSegments(IPath anotherPath);

	public IPath removeFileExtension();

	public IPath removeFirstSegments(int count);

	public IPath removeLastSegments(int count);

	public IPath removeTrailingSeparator();

	public String segment(int index);

	public int segmentCount();

	public String[] segments();

	public IPath setDevice(String device);

	public java.io.File toFile();

	public String toOSString();

	public String toPortableString();

	public String toString();

	public IPath uptoSegment(int count);
}
