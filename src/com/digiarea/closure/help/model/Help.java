package com.digiarea.closure.help.model;

import java.util.List;

public class Help {

	private List<HelpTopic> topics;

	public Help(List<HelpTopic> topics) {
		super();
		this.topics = topics;
	}

	public List<HelpTopic> getTopics() {
		return topics;
	}

}
