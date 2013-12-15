package com.digiarea.closurefx.cli;

import java.io.File;

import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;

public class EditorLoader {

	public void load(Editor editor, String file, String line, String column) {
		switch (editor.getType()) {
		case KOMODO:
			KomodoEditor komodoEditor = new KomodoEditor(editor);
			komodoEditor.setFile(file);
			komodoEditor.setColumn(column);
			komodoEditor.setLine(line);
			komodoEditor.load();
			break;
		case NETBEANS:
			break;
		case NOTEPAD:
			NotepadEditor notepadEditor = new NotepadEditor(editor);
			notepadEditor.setFile(file);
			notepadEditor.setColumn(column);
			notepadEditor.setLine(line);
			notepadEditor.load();
			break;
		case SUBLIMTEXT:
			SublimtextEditor sublimtextEditor = new SublimtextEditor(editor);
			sublimtextEditor.setFile(file);
			sublimtextEditor.setColumn(column);
			sublimtextEditor.setLine(line);
			sublimtextEditor.load();
			break;
		case WEBSTORM:
			WebstormEditor webstormEditor = new WebstormEditor(editor);
			webstormEditor.setFile(file);
			webstormEditor.setColumn(column);
			webstormEditor.setLine(line);
			webstormEditor.load();
			break;
		case EMACS:
			EmacsEditor emacsEditor = new EmacsEditor(editor);
			emacsEditor.setFile(file);
			emacsEditor.setColumn(column);
			emacsEditor.setLine(line);
			emacsEditor.load();
			break;
		case TEXTMATE:
			TextMateEditor textMateEditor = new TextMateEditor(editor);
			textMateEditor.setFile(file);
			textMateEditor.setColumn(column);
			textMateEditor.setLine(line);
			textMateEditor.load();
			break;
		case JEDIT:
			JEditEditor jEditEditor = new JEditEditor(editor);
			jEditEditor.setFile(file);
			jEditEditor.setColumn(column);
			jEditEditor.setLine(line);
			jEditEditor.load();
			break;
		}
	}

	public static Boolean isValid(Editor editor) {
		if (editor != null && editor.getPath() != null
				&& !editor.getPath().isEmpty()) {
			switch (editor.getType()) {
			case KOMODO:
				KomodoEditor komodoEditor = new KomodoEditor(editor);
				return new File(komodoEditor.getFullPath()).exists();
			case NETBEANS:
				break;
			case NOTEPAD:
				NotepadEditor notepadEditor = new NotepadEditor(editor);
				return new File(notepadEditor.getFullPath()).exists();
			case SUBLIMTEXT:
				SublimtextEditor sublimtextEditor = new SublimtextEditor(editor);
				return new File(sublimtextEditor.getFullPath()).exists();
			case WEBSTORM:
				WebstormEditor webstormEditor = new WebstormEditor(editor);
				return new File(webstormEditor.getFullPath()).exists();
			case EMACS:
				EmacsEditor emacsEditor = new EmacsEditor(editor);
				return new File(emacsEditor.getFullPath()).exists();
			case TEXTMATE:
				TextMateEditor textMateEditor = new TextMateEditor(editor);
				return new File(textMateEditor.getFullPath()).exists();
			case JEDIT:
				JEditEditor jEditEditor = new JEditEditor(editor);
				return new File(jEditEditor.getFullPath()).exists();
			}
		}
		return false;
	}

	public static boolean isSupported(Editor editor) {
		if (editor != null) {
			OperatingSystemFamily family = getOSFamily();
			switch (editor.getType()) {
			case KOMODO:
				if (family == OperatingSystemFamily.WINDOWS
						|| family == OperatingSystemFamily.LINUX
						|| family == OperatingSystemFamily.UNIX
						|| family == OperatingSystemFamily.MAC) {
					return true;
				}
				break;
			case NETBEANS:
				break;
			case NOTEPAD:
				if (family == OperatingSystemFamily.WINDOWS) {
					return true;
				}
				break;
			case SUBLIMTEXT:
				if (family == OperatingSystemFamily.WINDOWS
						|| family == OperatingSystemFamily.MAC
						|| family == OperatingSystemFamily.LINUX
						|| family == OperatingSystemFamily.UNIX) {
					return true;
				}
				break;
			case WEBSTORM:
				if (family == OperatingSystemFamily.WINDOWS
						|| family == OperatingSystemFamily.MAC
						|| family == OperatingSystemFamily.LINUX
						|| family == OperatingSystemFamily.UNIX) {
					return true;
				}
				break;
			case EMACS:
				if (family == OperatingSystemFamily.WINDOWS
						|| family == OperatingSystemFamily.MAC
						|| family == OperatingSystemFamily.LINUX
						|| family == OperatingSystemFamily.UNIX) {
					return true;
				}
				break;
			case TEXTMATE:
				if (family == OperatingSystemFamily.MAC) {
					return true;
				}
				break;
			case JEDIT:
				if (family == OperatingSystemFamily.WINDOWS
						|| family == OperatingSystemFamily.MAC
						|| family == OperatingSystemFamily.LINUX
						|| family == OperatingSystemFamily.UNIX) {
					return true;
				}
				break;
			}
		}
		return false;
	}

	public static String getOS() {
		return System.getProperty("os.name");
	}

	public static OperatingSystemFamily getOSFamily() {
		return OperatingSystem.resolve(getOS()).getFamily();
	}

}
