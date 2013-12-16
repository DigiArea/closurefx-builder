package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.StatusDialogController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSHelperSectionController extends ClosureController implements Initializable {

    @FXML
    private TitledPane section;

    public JSHelperSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleWhitespaceButton(ActionEvent event) {
        StatusDialogController controller = DialogFactory.getStatusDialog(bundle, new Status(StatusType.WARNING, bundle.getString(IConstants.JSHelperSection_Msg), null), bundle.getString(IConstants.JSHelperSection));
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.applyWhitespacesOptions();
        }
    }

    @FXML
    private void handleSimpleButton(ActionEvent event) {
        StatusDialogController controller = DialogFactory.getStatusDialog(bundle, new Status(StatusType.WARNING, bundle.getString(IConstants.JSHelperSection_Msg), null), bundle.getString(IConstants.JSHelperSection));
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.applySimpleOptions();
        }
    }

    @FXML
    private void handleAdvancedButton(ActionEvent event) {
        StatusDialogController controller = DialogFactory.getStatusDialog(bundle, new Status(StatusType.WARNING, bundle.getString(IConstants.JSHelperSection_Msg), null), bundle.getString(IConstants.JSHelperSection));
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.applyAdvancedOptions();
        }
    }

    @FXML
    private void handleQuiteButton(ActionEvent event) {
        modelFacade.applyQuietWarningLevel();
    }

    @FXML
    private void handleDefaultButton(ActionEvent event) {
        modelFacade.applyDefaultWarningLevel();
    }

    @FXML
    private void handleVerboseButton(ActionEvent event) {
        modelFacade.applyVerboseWarningLevel();
    }

    public TitledPane getSection() {
        return section;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane17 = new AnchorPane();
        anchorPane17.setId("AnchorPane");
        anchorPane17.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane17.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane17.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane17.setPrefWidth(Control.USE_COMPUTED_SIZE);
        section = new TitledPane();
        section.setAnimated(false);
        section.setCollapsible(false);
        section.setPrefHeight(Control.USE_COMPUTED_SIZE);
        section.setPrefWidth(Control.USE_COMPUTED_SIZE);
        section.setText(bundle.getString("JSHelperSection"));
        AnchorPane.setBottomAnchor(section, 0.0);
        AnchorPane.setLeftAnchor(section, 0.0);
        AnchorPane.setRightAnchor(section, 0.0);
        AnchorPane.setTopAnchor(section, 0.0);
        VBox vBox28 = new VBox();
        vBox28.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox28.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox28.setSpacing(5.0);
        Label label27 = new Label();
        label27.setText(bundle.getString("JSHelperSection_Desc"));
        label27.setWrapText(true);
        vBox28.getChildren().add(label27);
        GridPane gridPane38 = new GridPane();
        gridPane38.setHgap(10.0);
        gridPane38.setVgap(5.0);
        Button button31 = new Button();
        button31.setAlignment(Pos.CENTER_LEFT);
        button31.setMaxWidth(1.7976931348623157E308);
        button31.setMnemonicParsing(false);
        button31.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleWhitespaceButton(event);
            }
        });
        button31.getStyleClass().add("invisible-button");
        button31.setText(bundle.getString("JSHelperSection_Whitespaces"));
        GridPane.setColumnIndex(button31, 0);
        GridPane.setRowIndex(button31, 1);
        ImageView imageView18 = new ImageView();
        imageView18.setFitHeight(16.0);
        imageView18.setFitWidth(16.0);
        imageView18.setMouseTransparent(true);
        imageView18.setPickOnBounds(true);
        imageView18.setPreserveRatio(true);
        Image image18 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/optimization-whitespases.png").openStream());
        imageView18.setImage(image18);
        button31.setGraphic(imageView18);
        gridPane38.getChildren().add(button31);
        Button button32 = new Button();
        button32.setAlignment(Pos.CENTER_LEFT);
        button32.setMaxWidth(1.7976931348623157E308);
        button32.setMnemonicParsing(false);
        button32.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleSimpleButton(event);
            }
        });
        button32.getStyleClass().add("invisible-button");
        button32.setText(bundle.getString("JSHelperSection_Simple"));
        GridPane.setColumnIndex(button32, 0);
        GridPane.setRowIndex(button32, 2);
        ImageView imageView19 = new ImageView();
        imageView19.setFitHeight(16.0);
        imageView19.setFitWidth(16.0);
        imageView19.setMouseTransparent(true);
        imageView19.setPickOnBounds(true);
        imageView19.setPreserveRatio(true);
        Image image19 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/optimization-simple.png").openStream());
        imageView19.setImage(image19);
        button32.setGraphic(imageView19);
        gridPane38.getChildren().add(button32);
        Button button33 = new Button();
        button33.setAlignment(Pos.CENTER_LEFT);
        button33.setMaxWidth(1.7976931348623157E308);
        button33.setMnemonicParsing(false);
        button33.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAdvancedButton(event);
            }
        });
        button33.getStyleClass().add("invisible-button");
        button33.setText(bundle.getString("JSHelperSection_Advance"));
        GridPane.setColumnIndex(button33, 0);
        GridPane.setRowIndex(button33, 3);
        ImageView imageView20 = new ImageView();
        imageView20.setFitHeight(16.0);
        imageView20.setFitWidth(16.0);
        imageView20.setMouseTransparent(true);
        imageView20.setPickOnBounds(true);
        imageView20.setPreserveRatio(true);
        Image image20 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/optimization-advanced.png").openStream());
        imageView20.setImage(image20);
        button33.setGraphic(imageView20);
        gridPane38.getChildren().add(button33);
        Button button34 = new Button();
        button34.setAlignment(Pos.CENTER_LEFT);
        button34.setMaxWidth(1.7976931348623157E308);
        button34.setMnemonicParsing(false);
        button34.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleDefaultButton(event);
            }
        });
        button34.getStyleClass().add("invisible-button");
        button34.setText(bundle.getString("JSHelperSection_Default"));
        GridPane.setColumnIndex(button34, 1);
        GridPane.setRowIndex(button34, 1);
        ImageView imageView21 = new ImageView();
        imageView21.setFitHeight(16.0);
        imageView21.setFitWidth(16.0);
        imageView21.setMouseTransparent(true);
        imageView21.setPickOnBounds(true);
        imageView21.setPreserveRatio(true);
        Image image21 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-exclamation.png").openStream());
        imageView21.setImage(image21);
        button34.setGraphic(imageView21);
        gridPane38.getChildren().add(button34);
        Button button35 = new Button();
        button35.setAlignment(Pos.CENTER_LEFT);
        button35.setMaxWidth(1.7976931348623157E308);
        button35.setMnemonicParsing(false);
        button35.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleQuiteButton(event);
            }
        });
        button35.getStyleClass().add("invisible-button");
        button35.setText(bundle.getString("JSHelperSection_Quite"));
        GridPane.setColumnIndex(button35, 1);
        GridPane.setRowIndex(button35, 2);
        ImageView imageView22 = new ImageView();
        imageView22.setFitHeight(16.0);
        imageView22.setFitWidth(16.0);
        imageView22.setMouseTransparent(true);
        imageView22.setPickOnBounds(true);
        imageView22.setPreserveRatio(true);
        Image image22 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-off.png").openStream());
        imageView22.setImage(image22);
        button35.setGraphic(imageView22);
        gridPane38.getChildren().add(button35);
        Button button36 = new Button();
        button36.setAlignment(Pos.CENTER_LEFT);
        button36.setMaxWidth(1.7976931348623157E308);
        button36.setMnemonicParsing(false);
        button36.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleVerboseButton(event);
            }
        });
        button36.getStyleClass().add("invisible-button");
        button36.setText(bundle.getString("JSHelperSection_Verbose"));
        GridPane.setColumnIndex(button36, 1);
        GridPane.setRowIndex(button36, 3);
        ImageView imageView23 = new ImageView();
        imageView23.setFitHeight(16.0);
        imageView23.setFitWidth(16.0);
        imageView23.setMouseTransparent(true);
        imageView23.setPickOnBounds(true);
        imageView23.setPreserveRatio(true);
        Image image23 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-error.png").openStream());
        imageView23.setImage(image23);
        button36.setGraphic(imageView23);
        gridPane38.getChildren().add(button36);
        Label label28 = new Label();
        label28.setText(bundle.getString("JSHelperSection_Optimization"));
        label28.setWrapText(true);
        GridPane.setColumnIndex(label28, 0);
        GridPane.setRowIndex(label28, 0);
        gridPane38.getChildren().add(label28);
        Label label29 = new Label();
        label29.setText(bundle.getString("JSHelperSection_Warning"));
        GridPane.setColumnIndex(label29, 1);
        GridPane.setRowIndex(label29, 0);
        gridPane38.getChildren().add(label29);
        ColumnConstraints columnConstraints78 = new ColumnConstraints();
        columnConstraints78.setHgrow(Priority.NEVER);
        columnConstraints78.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints78.setPercentWidth(-1.0);
        columnConstraints78.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane38.getColumnConstraints().add(columnConstraints78);
        ColumnConstraints columnConstraints79 = new ColumnConstraints();
        columnConstraints79.setHgrow(Priority.NEVER);
        columnConstraints79.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints79.setPercentWidth(-1.0);
        columnConstraints79.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane38.getColumnConstraints().add(columnConstraints79);
        RowConstraints rowConstraints66 = new RowConstraints();
        rowConstraints66.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints66.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints66.setVgrow(Priority.NEVER);
        gridPane38.getRowConstraints().add(rowConstraints66);
        RowConstraints rowConstraints67 = new RowConstraints();
        rowConstraints67.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints67.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints67.setVgrow(Priority.NEVER);
        gridPane38.getRowConstraints().add(rowConstraints67);
        RowConstraints rowConstraints68 = new RowConstraints();
        rowConstraints68.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints68.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints68.setVgrow(Priority.NEVER);
        gridPane38.getRowConstraints().add(rowConstraints68);
        RowConstraints rowConstraints69 = new RowConstraints();
        rowConstraints69.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints69.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints69.setVgrow(Priority.NEVER);
        gridPane38.getRowConstraints().add(rowConstraints69);
        vBox28.getChildren().add(gridPane38);
        Insets insets42 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox28.setPadding(insets42);
        section.setContent(vBox28);
        anchorPane17.getChildren().add(section);
        initialize(null, bundle);
        return anchorPane17;
    }

}
