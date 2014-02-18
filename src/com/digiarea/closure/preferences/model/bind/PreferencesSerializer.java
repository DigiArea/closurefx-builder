package com.digiarea.closure.preferences.model.bind;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import com.digiarea.closure.preferences.model.ClosureLibraries;
import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.EditorType;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.Variables;

public class PreferencesSerializer {

	private static String LIST_ITEM = "\n";
	private static String LIST_FIELD = "\t";

	public static String PREFS_EDITORS = "PREFS_EDITORS";
	public static String PREFS_VARIABLES = "PREFS_VARIABLES";
	public static String PREFS_CLOSURE_LIBRARIES = "PREFS_CLOSURE_LIBRARIES";

	public com.digiarea.closure.preferences.model.Preferences read() {
		Editors editors = readEditors();
		Variables variables = readVariables();
		ClosureLibraries libraries = readLibraries();
		return new com.digiarea.closure.preferences.model.Preferences(editors,
				variables, libraries);
	}

	public Variable readVariable(String name) {
		Variables libraries = readVariables();
		for (Variable closureLibrary : libraries.getVariables()) {
			if (closureLibrary.getPlaceholder().equals(name)) {
				return closureLibrary;
			}
		}
		return null;
	}

	public ClosureLibrary readLibrary(String name) {
		ClosureLibraries libraries = readLibraries();
		for (ClosureLibrary closureLibrary : libraries.getLibraries()) {
			if (closureLibrary.getPlaceholder().equals(name)) {
				return closureLibrary;
			}
		}
		return null;
	}

	public ClosureLibraries readLibraries() {
		Preferences prefs = Preferences.userRoot().node(
				this.getClass().getName());
		List<ClosureLibrary> ed = parseLibraries(prefs.get(
				PREFS_CLOSURE_LIBRARIES, ""));
		return new ClosureLibraries(ed);
	}

	public Variables readVariables() {
		Preferences prefs = Preferences.userRoot().node(
				this.getClass().getName());
		List<Variable> ed = parseVariables(prefs.get(PREFS_VARIABLES, ""));
		return new Variables(ed);
	}

	public Editors readEditors() {
		Preferences prefs = Preferences.userRoot().node(
				this.getClass().getName());
		List<Editor> ed = parseEditors(prefs.get(PREFS_EDITORS, ""));
		List<Editor> defaultEditors = getDefaultEditors();
		if (ed == null || ed.isEmpty()) {
			ed = defaultEditors;
		} else {
			for (Editor editor : defaultEditors) {
				// new editor appear
				if (!ed.contains(editor)) {
					ed.add(editor);
				}
			}
		}
		return new Editors(ed);
	}

	public void write(Editors editors) {
		Preferences prefs = Preferences.userRoot().node(
				this.getClass().getName());
		prefs.put(PREFS_EDITORS, parseEditors(editors));
	}

	public void write(Variables variables) {
		Preferences prefs = Preferences.userRoot().node(
				this.getClass().getName());
		prefs.put(PREFS_VARIABLES, parseVariables(variables));
	}

	public void write(ClosureLibraries closureLibraries) {
		Preferences prefs = Preferences.userRoot().node(
				this.getClass().getName());
		prefs.put(PREFS_CLOSURE_LIBRARIES, parseLibraries(closureLibraries));
	}

	public Editor readDefaultEditor() {
		Editors editors = readEditors();
		for (Editor editor : editors.getEditors()) {
			if (editor.isDefault()) {
				return editor;
			}
		}
		return null;
	}

	private List<Editor> getDefaultEditors() {
		List<Editor> editors = new ArrayList<Editor>();

		Editor komodo = new Editor();
		komodo.setName("Komodo Edit");
		komodo.setType(EditorType.KOMODO);

		Editor notepad = new Editor();
		notepad.setName("Notepad++");
		notepad.setType(EditorType.NOTEPAD);

		Editor sublimtext = new Editor();
		sublimtext.setName("Sublime Text");
		sublimtext.setType(EditorType.SUBLIMTEXT);

		Editor webstorm = new Editor();
		webstorm.setName("WebStorm");
		webstorm.setType(EditorType.WEBSTORM);

		Editor emacs = new Editor();
		emacs.setName("Emacs");
		emacs.setType(EditorType.EMACS);

		Editor textmate = new Editor();
		textmate.setName("TextMate");
		textmate.setType(EditorType.TEXTMATE);

		Editor jedit = new Editor();
		jedit.setName("jEdit");
		jedit.setType(EditorType.JEDIT);

		editors.add(webstorm);
		editors.add(komodo);
		editors.add(notepad);
		editors.add(sublimtext);
		editors.add(emacs);
		editors.add(textmate);
		editors.add(jedit);

		return editors;
	}

	private List<ClosureLibrary> parseLibraries(String libraries) {
		List<ClosureLibrary> listVariables = new ArrayList<ClosureLibrary>();
		if (libraries != null && !libraries.isEmpty()) {
			String[] list = libraries.split(LIST_ITEM);
			for (String string : list) {
				String[] variableList = string.split(LIST_FIELD);
				if (variableList.length == 2) {
					ClosureLibrary variable = new ClosureLibrary(
							variableList[0], variableList[1]);
					listVariables.add(variable);
				}
			}
		}
		return listVariables;
	}

	private String parseLibraries(ClosureLibraries closureLibraries) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (closureLibraries != null
				&& !closureLibraries.getLibraries().isEmpty()) {
			for (ClosureLibrary var : closureLibraries.getLibraries()) {
				if (var.getName() != null) {
					stringBuilder.append(var.getName());
				}
				stringBuilder.append(LIST_FIELD);
				if (var.getPath() != null) {
					stringBuilder.append(var.getPath());
				}
				stringBuilder.append(LIST_ITEM);
			}
		}
		return stringBuilder.toString();
	}

	private List<Variable> parseVariables(String variables) {
		List<Variable> listVariables = new ArrayList<Variable>();
		if (variables != null && !variables.isEmpty()) {
			String[] list = variables.split(LIST_ITEM);
			for (String string : list) {
				String[] variableList = string.split(LIST_FIELD);
				if (variableList.length == 2) {
					Variable variable = new Variable(variableList[0],
							variableList[1]);
					listVariables.add(variable);
				}
			}
		}
		return listVariables;
	}

	private String parseVariables(Variables variables) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (variables != null && !variables.getVariables().isEmpty()) {
			for (Variable var : variables.getVariables()) {
				if (var.getName() != null) {
					stringBuilder.append(var.getName());
				}
				stringBuilder.append(LIST_FIELD);
				if (var.getPath() != null) {
					stringBuilder.append(var.getPath());
				}
				stringBuilder.append(LIST_ITEM);
			}
		}
		return stringBuilder.toString();
	}

	private List<Editor> parseEditors(String editors) {
		List<Editor> listEditors = new ArrayList<Editor>();
		if (editors != null && !editors.isEmpty()) {
			String[] list = editors.split(LIST_ITEM);
			for (String string : list) {
				String[] editorList = string.split(LIST_FIELD);
				if (editorList.length == 7) {
					Editor editor = new Editor(editorList[0], editorList[1],
							editorList[2], editorList[3], editorList[4],
							Boolean.parseBoolean(editorList[5]),
							EditorType.valueOf(editorList[6]));
					listEditors.add(editor);
				}
			}
		}
		return listEditors;
	}

	private String parseEditors(Editors editors) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (editors != null && !editors.getEditors().isEmpty()) {
			for (Editor editor : editors.getEditors()) {
				if (editor.getName() != null) {
					stringBuilder.append(editor.getName());
				}
				stringBuilder.append(LIST_FIELD);
				if (editor.getPath() != null) {
					stringBuilder.append(editor.getPath());
				}
				stringBuilder.append(LIST_FIELD);
				if (editor.getFile() != null) {
					stringBuilder.append(editor.getFile());
				}
				stringBuilder.append(LIST_FIELD);
				if (editor.getLine() != null) {
					stringBuilder.append(editor.getLine());
				}
				stringBuilder.append(LIST_FIELD);
				if (editor.getColumn() != null) {
					stringBuilder.append(editor.getColumn());
				}
				stringBuilder.append(LIST_FIELD);
				stringBuilder.append(editor.isDefault());
				stringBuilder.append(LIST_FIELD);
				if (editor.getType() != null) {
					stringBuilder.append(editor.getType().name());
				}
				stringBuilder.append(LIST_ITEM);
			}
		}
		return stringBuilder.toString();
	}

}
