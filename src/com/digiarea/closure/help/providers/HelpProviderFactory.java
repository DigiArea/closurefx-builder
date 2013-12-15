package com.digiarea.closure.help.providers;

public class HelpProviderFactory {

	public static IHelpProvider getHelp() {
		return new HelpProvider();
	}

}
