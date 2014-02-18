package com.digiarea.closurefx;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TabPane;
import javafx.util.Callback;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.core.PathResolverFactory;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.bind.Binder;
import com.digiarea.closure.model.bind.Checker;
import com.digiarea.closure.model.bind.ControllerFactory;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.controller.ClosureEditorController;
import com.digiarea.closure.model.validation.MessageManager;
import com.digiarea.closure.model.validation.Validator;
import com.digiarea.closure.model.zippy.ZippyClosureSerializer;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

public class Document {

	private StringProperty name = new SimpleStringProperty(this, "name");
	private ObjectProperty<File> file = new SimpleObjectProperty<File>(this,
			"file");
	private Callback<Class<?>, Object> factory;
	private ResourceBundle bundle;
	private boolean needSave = true;
	private Validator validator;
	private MessageManager manager;
	private ModelFacade modelFacade;
	private IPathResolver pathResolver;
	private Binder binder;

	private Closure closure;

	public Document(File file) {
		setFile(file);
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getName() {
		if (name.get().isEmpty() && getFile() != null) {
			setName(getFile().getName());
		}
		return name.get();
	}

	public ObjectProperty<File> fileProperty() {
		return file;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public Validator getValidator() {
		return validator;
	}

	public ModelFacade getModelFacade() {
		return modelFacade;
	}

	public IPathResolver getPathResolver() {
		if (pathResolver == null) {
			pathResolver = PathResolverFactory.getResolver(getFile());
		}
		return pathResolver;
	}

	public Binder getBinder() {
		return binder;
	}

	public TabPane load() {
		try {
			// MODEL
			if (closure == null) {
				ZippyClosureSerializer closureSerializer = new ZippyClosureSerializer();
				if (getFile() == null) {
					closure = closureSerializer.read(getClass().getResource(
							"resources/save.closure").openStream());
				} else {
					closure = closureSerializer.read(getFile()
							.getAbsolutePath());
				}
			}

			// CHECKER
			Checker checker = new Checker();
			closure.accept(checker, null);

			// make ModelFacade and the ControllerFactory
			manager = new MessageManager();
			modelFacade = new ModelFacade(closure);
			factory = new ControllerFactory(modelFacade, bundle);
			modelFacade.setDocument(this);
			modelFacade.setFactory(factory);
			// LOAD UI
			// TODO abstract the loading process
			// FXMLLoader loader = new FXMLLoader(
			// getClass()
			// .getResource(
			// "/com/digiarea/closure/model/controller/ClosureEditor.fxml"));
			// if (bundle != null) {
			// loader.setResources(bundle);
			// }
			// loader.setControllerFactory(factory);
			// TabPane editor = (TabPane) loader.load();
			Object call = factory.call(ClosureEditorController.class);
			TabPane editor = ((ClosureEditorController) call).create();

			// BINDING
			binder = new Binder(factory);
			closure.accept(binder, null);

			// VALIDATION
			validator = new Validator(bundle, factory, manager, pathResolver);
			closure.accept(validator, null);

			DocumentBinder documentBinder = new DocumentBinder(factory, this);
			documentBinder.bind();

			return editor;
		} catch (IOException ex) {
			Logger.getLogger(ClosureFXController.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (Exception e) {
			Logger.getLogger(ClosureFXController.class.getName()).log(
					Level.SEVERE, null, e);
		}
		return null;
	}

	public IStatus save() {
		try {
			if (getFile() == null) {
				return Status.NO_STATUS;
			}
			if (!getFile().getName().endsWith(
					"." + IConstants.EXTENSION_CLOSURE)) {
				setFile(new File(getFile().getAbsolutePath() + "."
						+ IConstants.EXTENSION_CLOSURE));
			}
			if (!getFile().exists()) {
				getFile().createNewFile();
			}
			ZippyClosureSerializer closureSerializer = new ZippyClosureSerializer();
			closureSerializer.write(closure, getFile().getAbsolutePath());
			setName(getFile().getName());
			// setNeedSave(false);
			return Status.OK_STATUS;
		} catch (Exception e) {
			Logger.getLogger(ClosureFXController.class.getName()).log(
					Level.SEVERE, null, e);
			return Status.NO_STATUS;
		}
	}

	public void setClosure(Closure closure) {
		this.closure = closure;
	}

	public Closure getClosure() {
		return closure;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public Callback<Class<?>, Object> getFactory() {
		return factory;
	}

	public File getFile() {
		return file.getValue();
	}

	public String getPath() {
		if (getFile() != null) {
			return getFile().getAbsolutePath();
		}
		return null;
	}

	public void setFile(File file) {
		this.file.setValue(file);

		if (getFile() != null) {
			getPathResolver().setBasePath((getFile().getAbsolutePath()));
		}
	}

	public boolean needSave() {
		return needSave;
	}

	public void setNeedSave(boolean needSave) {
		this.needSave = needSave;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Document) {
			// equal by File or Closure model
			Document document = (Document) obj;
			return (document.getFile() != null && getFile() != null && getFile()
					.equals(document.getFile()))
					|| document.getClosure().equals(closure);
		}
		return super.equals(obj);
	}
}
