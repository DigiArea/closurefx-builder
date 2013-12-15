package com.digiarea.closure.model.controller;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;

public class UIUtils {

    public static File saveFile(String title, String file, String... extension) {
        FileChooser fileChooser = new FileChooser();
        if (title != null) {
            fileChooser.setTitle(title);
        }
        if (file != null && extension != null) {
            FileChooser.ExtensionFilter mwFilter = new FileChooser.ExtensionFilter(file, extension);
            fileChooser.getExtensionFilters().add(mwFilter);
        }
        return fileChooser.showSaveDialog(null);
    }

    public static File chooseFile(String title, String file, String... extension) {
        FileChooser fileChooser = new FileChooser();
        if (title != null) {
            fileChooser.setTitle(title);
        }
        if (file != null && extension != null) {
            FileChooser.ExtensionFilter mwFilter = new FileChooser.ExtensionFilter(file, extension);
            fileChooser.getExtensionFilters().add(mwFilter);
        }
        return fileChooser.showOpenDialog(null);
    }

    public static File chooseFolder(File file, String title) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        if (title != null) {
            directoryChooser.setTitle(title);
        }
        if (file != null) {
            directoryChooser.setInitialDirectory(file);
        }
        return directoryChooser.showDialog(null);
    }

}
