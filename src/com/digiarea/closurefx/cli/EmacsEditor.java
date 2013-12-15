package com.digiarea.closurefx.cli;

import java.io.IOException;

import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;

public class EmacsEditor {

	private static String EMACS_MAC = "/Contents/MacOS/Emacs";
	private static String EMACS_WIN = "/bin/emacs.exe";
	private static String EMACS_UNIX = "/emacs";

	private String file;
	private String line = "0";
	private String column = "0";
	private String path;

	private OperatingSystemFamily osFamily;

	public EmacsEditor(Editor editor) {
		this.path = editor.getPath();
	}

	public EmacsEditor(String path) {
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
			return path + EMACS_UNIX;
		case MAC:
			return path + EMACS_MAC;
		case UNIX:
			return path + EMACS_UNIX;
		case WINDOWS:
			return path + EMACS_WIN;
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
			return new ProcessBuilder(getFullPath(), "+" + line + ":" + column,
					file);
		}
		return null;
	}

	public String getName() {
		return "Sublime Text";
	}

}
