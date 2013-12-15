package com.digiarea.closurefx.cli;

import java.io.IOException;

import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;

public class WebstormEditor {

	private static String WEBSTORM_WIN = "/bin/WebStorm.exe";
	private static String WEBSTORM_MAC = "/Contents/MacOS/idea_appLauncher";
	private static String WEBSTORM_UNIX = "/bin/webstorm.sh";

	private String file;
	private String line = "0";
	private String column = "0";
	private String path;
	private OperatingSystemFamily osFamily;

	public WebstormEditor(Editor editor) {
		this.path = editor.getPath();
	}

	public WebstormEditor(String path) {
		super();
		this.path = path;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}

	public String getOS() {
		return System.getProperty("os.name");
	}

	public OperatingSystemFamily getOSFamily() {
		if (osFamily == null) {
			osFamily = OperatingSystem.resolve(getOS()).getFamily();
		}
		return osFamily;
	}

	public String getFullPath() {
		switch (getOSFamily()) {
		case DEC_OS:
			break;
		case LINUX:
			return path + WEBSTORM_UNIX;
		case MAC:
			return path + WEBSTORM_MAC;
		case UNIX:
			return path + WEBSTORM_UNIX;
		case WINDOWS:
			return path + WEBSTORM_WIN;
		}
		return null;
	}

	public ProcessBuilder getProcessBuilder() {
		switch (getOSFamily()) {
		case DEC_OS:
			break;
		case LINUX:
		case MAC:
		case UNIX:
		case WINDOWS:
			return new ProcessBuilder(getFullPath(), "--line", line, file);
		}
		return null;
	}

	public void load() {
		if (getFullPath() != null) {
			try {
				ProcessBuilder builder = getProcessBuilder();
				builder.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getName() {
		return "WebStorm";
	}

}
