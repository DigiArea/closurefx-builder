package com.digiarea.closurefx.editors;

import java.io.IOException;

import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;

public class NotepadEditor {

	private Editor editor;

	private static String NOTEPAD_WIN = "/notepad++.exe";

	private String file;
	private String line;
	private String column;

	private OperatingSystemFamily osFamily;

	public NotepadEditor(Editor editor) {
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
			break;
		case UNIX:
			break;
		case WINDOWS:
			return editor.getPath() + NOTEPAD_WIN;
		}
		return null;
	}

	public void load() {
		if (getFullPath() != null) {
			try {
				ProcessBuilder builder = new ProcessBuilder(getFullPath(), "-n"
						+ line, "-c" + column, file);
				builder.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ProcessBuilder getProcessBuilder() {
		return null;
	}

	public String getName() {
		return "Notepad++";
	}

}
