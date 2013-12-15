package com.digiarea.closurefx.cli;

import java.io.IOException;

import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;

public class SublimtextEditor {

	private static String SUBLIM_WIN = "/sublime_text.exe";
	private static String SUBLIM_MAC = "/Contents/SharedSupport/bin/subl";
	private static String SUBLIM_UNIX = "/sublime_text";

	private String file;
	private String line = "0";
	private String column = "0";
	private String path;

	private OperatingSystemFamily osFamily;

	public SublimtextEditor(Editor editor) {
		this.path = editor.getPath();
	}

	public SublimtextEditor(String path) {
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
			return path + SUBLIM_UNIX;
		case MAC:
			return path + SUBLIM_MAC;
		case UNIX:
			return path + SUBLIM_UNIX;
		case WINDOWS:
			return path + SUBLIM_WIN;
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

	public ProcessBuilder getProcessBuilder() {
		switch (getOSFamily()) {
		case DEC_OS:
			break;
		case LINUX:
		case MAC:
		case UNIX:
		case WINDOWS:
			return new ProcessBuilder(getFullPath(), file + ":" + line + ":"
					+ column);
		}
		return null;
	}

	public String getName() {
		return "Sublime Text";
	}

}
