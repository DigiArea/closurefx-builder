package com.digiarea.closure.help.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HelpTopic {

	private String name;
	private URL link;
	private boolean isRoot;

	private List<HelpTopic> children = new ArrayList<HelpTopic>();

	public HelpTopic(String name, URL link) {
		this.name = name;
		this.link = link;
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public List<HelpTopic> getChildren() {
		return children;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setChildren(List<HelpTopic> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public URL getLink() {
		return link;
	}

}
