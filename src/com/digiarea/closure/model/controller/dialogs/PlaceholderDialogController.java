package com.digiarea.closure.model.controller.dialogs;

import java.net.URL;
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

import com.digiarea.closure.model.entity.IPlaceholder;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class PlaceholderDialogController implements Initializable {

    @FXML
    private ListView<IPlaceholder> listView;

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
    private IPlaceholder placeholder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<IPlaceholder>, ListCell<IPlaceholder>>() {

            @Override
            public ListCell<IPlaceholder> call(ListView<IPlaceholder> list) {
                return new PlaceholderDialogController.PlaceholderCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IPlaceholder>() {

            @Override
            public void changed(ObservableValue<? extends IPlaceholder> arg0, IPlaceholder arg1, IPlaceholder arg2) {
                txtDescription.setText(arg2.getDescription());
            }
        });
    }

    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        placeholder = listView.getSelectionModel().getSelectedItem();
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

    public void setPlaceholders(List<? extends IPlaceholder> placeholders) {
        ObservableList<IPlaceholder> items = FXCollections.observableArrayList(placeholders);
        listView.setItems(items);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public IPlaceholder getPlaceholder() {
        return placeholder;
    }

    static class PlaceholderCell extends ListCell<IPlaceholder> {

        public void updateItem(IPlaceholder item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            }
            if (item != null) {
                setText(item.getValue());
            }
        }

    }

    public ListView<IPlaceholder> getListView() {
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
