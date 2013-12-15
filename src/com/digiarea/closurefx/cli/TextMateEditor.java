package com.digiarea.closurefx.cli;

import java.io.IOException;

import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;

public class TextMateEditor {

	private static String TEXTMATE_MAC = "/Contents/Resources/mate";

	private String file;
	private String line = "0";
	private String column = "0";
	private String path;

	private OperatingSystemFamily osFamily;

	public TextMateEditor(Editor editor) {
		this.path = editor.getPath();
	}

	public TextMateEditor(String path) {
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
			break;
		case MAC:
			return path + TEXTMATE_MAC;
		case UNIX:
			break;
		case WINDOWS:
			break;
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
			break;
		case MAC:
			return new ProcessBuilder(getFullPath(), "-l" + line, file);
		case UNIX:
			break;
		case WINDOWS:
			break;
		}
		return null;
	}

	public String getName() {
		return "Sublime Text";
	}

}
