package com.digiarea.closurefx.cli;

import java.io.IOException;

import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;

public class KomodoEditor {

	private static String KOMODO_WIN = "/komodo.exe";
	private static String KOMODO_UNIX = "/bin/komodo";
	private static String KOMODO_MAC = "/Applications/Komodo.app";

	private OperatingSystemFamily osFamily;
	private Editor editor;
	private String file;
	private String line;
	private String column;

	public KomodoEditor(Editor editor) {
		this.editor = editor;
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
			return editor.getPath() + KOMODO_UNIX;
		case MAC:
			return editor.getPath() + KOMODO_MAC;
		case UNIX:
			return editor.getPath() + KOMODO_UNIX;
		case WINDOWS:
			return editor.getPath() + KOMODO_WIN;
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
			return new ProcessBuilder(getFullPath(), "-l", line, file);
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
		return "Komodo Edit";
	}

}
