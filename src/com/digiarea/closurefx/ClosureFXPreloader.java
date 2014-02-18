/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digiarea.closurefx;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Closure FX Preloader
 *
 * @author daginno
 */
public class ClosureFXPreloader extends Preloader {
    
    ProgressBar bar;
    Stage stage;
    
    private Scene createPreloaderScene() {
        ImageView splash = new ImageView(new Image(
                getClass()
                .getResourceAsStream("resources/splash.jpg")));
        bar = new ProgressBar();
        bar.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(bar, Priority.NEVER);
        VBox.setVgrow(splash, Priority.NEVER);
        HBox.setHgrow(bar, Priority.ALWAYS);
        VBox p = new VBox();
        p.getChildren().add(splash);
        p.getChildren().add(bar);
        return new Scene(p);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createPreloaderScene());
        stage.show();
    }
    
    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
    
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }
}
