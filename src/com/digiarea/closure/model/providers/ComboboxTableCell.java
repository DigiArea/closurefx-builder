package com.digiarea.closure.model.providers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;

import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefineType;

public class ComboboxTableCell extends TableCell<JsDefine, JsDefineType> {

    private final ObservableList<JsDefineType> items;

    private ComboBox<JsDefineType> box;

    public ComboboxTableCell() {
        this(FXCollections.<JsDefineType>observableArrayList());
    }

    public ComboboxTableCell(JsDefineType... items) {
        this(FXCollections.observableArrayList(items));
    }

    public ComboboxTableCell(ObservableList<JsDefineType> items) {
        this.getStyleClass().add("combo-box-table-cell");
        this.items = items;
    }

    public ObservableList<JsDefineType> getItems() {
        return items;
    }

    /** {@inheritDoc} */
    @Override
    public void updateItem(JsDefineType item, boolean empty) {
        super.updateItem(item, empty);
        if (isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            if (box == null) {
                box = createComboBox(items);
            }
            setText(null);
            setGraphic(box);
            box.getSelectionModel().select(getItem());
        }
    }

    private ComboBox<JsDefineType> createComboBox(ObservableList<JsDefineType> items) {
        ComboBox<JsDefineType> box = new ComboBox<JsDefineType>(items);
        box.setMaxWidth(Double.MAX_VALUE);
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<JsDefineType>() {

            @Override
            public void changed(ObservableValue<? extends JsDefineType> ov, JsDefineType oldValue, JsDefineType newValue) {
                if (newValue != getItem()) {
                    ((JsDefine) getTableRow().getItem()).setType(newValue);
                }
            }
        });
        return box;
    }

}
