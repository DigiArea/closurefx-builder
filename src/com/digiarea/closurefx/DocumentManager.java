package com.digiarea.closurefx;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.digiarea.closure.model.controller.UIUtils;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.SaveDialogController;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

public class DocumentManager {

	private List<Document> documents;

	public DocumentManager() {
		this(new ArrayList<Document>());
	}

	public DocumentManager(List<Document> documents) {
		this.documents = documents;
	}

	/**
	 * @param document
	 * @return false when the manager already contains the document. Otherwise
	 *         true.
	 */
	public boolean addDocument(Document document) {
		if (document != null && documents.contains(document)) {
			return false;
		}
		documents.add(document);
		return true;
	}

	public boolean removeDocument(Document document) {
		return documents.remove(document);
	}

	public void removeAll() {
		documents.clear();
	}

	public int getIndex(Document document) {
		return documents.indexOf(document);
	}

	public Document getDocument(int index) {
		try {
			return documents.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public int getDocumentsCount() {
		return documents.size();
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void saveWithoutPromtAll(ResourceBundle bundle) {
		for (Document document : documents) {
			saveWithoutPromt(document, bundle);
		}
	}

	public IStatus saveWithoutPromt(Document document, ResourceBundle bundle) {
		if (document != null) {
			if (document.needSave()) {
				if (document.getFile() == null) {
					File file = UIUtils.saveFile("Select file",
							IConstants.CLOSURE_BUILD_FILE,
							IConstants.EXTENSION__CLOSURE);
					document.setFile(file);
				}
				return document.save();
			}
		}
		return Status.NO_STATUS;
	}

	public IStatus saveDocument(Document document, ResourceBundle bundle) {
		if (document != null) {
			if (document.needSave()) {
				SaveDialogController save = (SaveDialogController) DialogFactory
						.getSaveDialog(bundle, document.getName());
				if (save.getStatus() == Status.OK_STATUS) {
					if (document.getFile() == null) {
						File file = UIUtils.saveFile("Select file",
								IConstants.CLOSURE_BUILD_FILE,
								IConstants.EXTENSION__CLOSURE);
						document.setFile(file);
					}
					return document.save();
				}
				return save.getStatus();
			}
		}
		return Status.NO_STATUS;
	}

}
