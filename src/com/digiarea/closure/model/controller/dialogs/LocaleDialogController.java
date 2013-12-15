package com.digiarea.closure.model.controller.dialogs;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class LocaleDialogController implements Initializable {

    @FXML
    private ListView<Locale> listView;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelDesc;

    private Stage stage;

    /**
     * Selected placeholder.
     */
    private Locale locale;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Locale>, ListCell<Locale>>() {

            @Override
            public ListCell<Locale> call(ListView<Locale> list) {
                return new LocaleDialogController.PlaceholderCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Locale>() {

            @Override
            public void changed(ObservableValue<? extends Locale> arg0, Locale arg1, Locale arg2) {
                txtDescription.setText(arg2.getDisplayName());
            }
        });
    }

    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        locale = listView.getSelectionModel().getSelectedItem();
        stage.close();
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        stage.close();
    }

    public void setLabelDesc(String labelDesc) {
        this.labelDesc.setText(labelDesc);
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle.setText(labelTitle);
    }

    public void setLocales(List<Locale> locales) {
        Comparator<Locale> comparator = new Comparator<Locale>() {

            public int compare(Locale c1, Locale c2) {
                return c1.toString().compareTo(c2.toString());
            }
        };
        Collections.sort(locales, comparator);
        ObservableList<Locale> items = FXCollections.observableArrayList(locales);
        listView.setItems(items);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Locale getLocale() {
        return locale;
    }

    static class PlaceholderCell extends ListCell<Locale> {

        public void updateItem(Locale item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            }
            if (item != null) {
                setText(item.toString());
            }
        }

    }

    public ListView<Locale> getListView() {
        return listView;
    }

    public TextArea getTxtDescription() {
        return txtDescription;
    }

    public Label getLabelTitle() {
        return labelTitle;
    }

    public Label getLabelDesc() {
        return labelDesc;
    }

}
