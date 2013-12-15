package com.digiarea.closure.model.controller.dialogs;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
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
public class CharsetDialogController implements Initializable {

    @FXML
    private ListView<Charset> listView;

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
    private Charset charset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Charset>, ListCell<Charset>>() {

            @Override
            public ListCell<Charset> call(ListView<Charset> list) {
                return new CharsetDialogController.CharsetCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Charset>() {

            @Override
            public void changed(ObservableValue<? extends Charset> arg0, Charset arg1, Charset arg2) {
                txtDescription.setText(arg2.displayName());
            }
        });
    }

    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        charset = listView.getSelectionModel().getSelectedItem();
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

    public void setCharsets(List<? extends Charset> charsets) {
        ObservableList<Charset> items = FXCollections.observableArrayList(charsets);
        listView.setItems(items);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Charset getCharset() {
        return charset;
    }

    static class CharsetCell extends ListCell<Charset> {

        public void updateItem(Charset item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            }
            if (item != null) {
                setText(item.displayName());
            }
        }

    }

    public ListView<Charset> getListView() {
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
