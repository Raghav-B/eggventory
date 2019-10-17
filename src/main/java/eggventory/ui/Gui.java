package eggventory.ui;

import eggventory.StockList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Gui extends Application {
    @FXML
    private TextField inputField;
    @FXML
    private TextArea outputField;
    @FXML
    private TableView outputTable;
    @FXML
    private ScrollPane outputTableScroll;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/Gui.fxml"));
            fxmlLoader.setController(this);
            Stage ap = fxmlLoader.load();
            ap.show();

            // Event handler for user pressing enter in the text input.
            inputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        print(inputField.getText());
                        inputField.setText("");
                    }
                }
            });

            outputField.setText("The idea is to use this text area to replace the Cli.print() command. "
                    + "That is, what was once printed in the Cli will now be printed in this text area");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Can be called to redraw the table output as and when needed. Takes in a StockList object
     * that it uses to redraw the entire table.
     * @param stockList Input StockList object to be used to draw the table.
     */
    public void drawTable(StockList stockList) {
        //...
    }

    /**
     * Prints text output in the outputField TextArea to essentially replace the Cli.print()
     * command.
     * @param printString The raw String to be printed out, after some extra formatting.
     */
    public void print(String printString) {
        outputField.setText(outputField.getText() + "\n" + printString);
        outputField.setScrollTop(Double.MAX_VALUE);
    }
}
