package eggventory.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Gui extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/Gui.fxml"));
            Stage ap = fxmlLoader.load();
            ap.show();

            //fxmlLoader.<Gui>getController().
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
