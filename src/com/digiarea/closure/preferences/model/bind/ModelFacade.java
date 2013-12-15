package com.digiarea.closure.preferences.model.bind;

import javafx.util.Callback;

import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.Preferences;
import com.digiarea.closure.preferences.model.Variable;

public class ModelFacade {

	protected Preferences prefs;
	protected Callback<Class<?>, Object> factory;
	protected PreferencesSerializer serializer = new PreferencesSerializer();

	public ModelFacade() {
	}

	public void setPrefs(Preferences prefs) {
		this.prefs = prefs;
	}

	public void setFactory(Callback<Class<?>, Object> factory) {
		this.factory = factory;
	}

	public Editors getEditors() {
		return prefs.getEditors();
	}

	public void updateEditorPath(Editor editor, String absolutePath) {
		editor.setPath(absolutePath);
	}

	public void removeVariable(Variable variable) {
		prefs.getVariables().removeVariables(variable);
	}

	public void removeClosureLibrary(ClosureLibrary library) {
		prefs.getClosureLibraries().removeLibraries(library);
	}

	public void saveEditors() {
		serializer.write(prefs.getEditors());
	}

	public void saveVariables() {
		serializer.write(prefs.getVariables());
	}

	public void addVariable(String name, String path) {
		prefs.getVariables().addVariables(new Variable(name, path));
	}

	public Preferences getPrefs() {
		return prefs;
	}

	public void saveClosureLibraries() {
		serializer.write(prefs.getClosureLibraries());
	}

	public void addClosureLibrary(String name, String path) {
		prefs.getClosureLibraries()
				.addLibraries(new ClosureLibrary(name, path));
	}

}
