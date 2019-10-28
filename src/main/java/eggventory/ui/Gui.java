package eggventory.ui;

import eggventory.StockList;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

//@@author Raghav-B
/**
 * This is a controller class used to control the Gui.fxml from the entry point for
 * our application, the Eggventory class. Inherits most of its functionality from Cli
 * to ensure backwards compatibility with testing and if we choose to fall back to a
 * Cli implementation. Overrides some Cli functionality to interface with the Gui instead
 * of Cli.
 */
public class Gui extends Ui  {
    @FXML
    private TextFlow textFlow;
    @FXML
    private TextArea outputField;
    @FXML
    private TableView<ArrayList<String>> outputTable;
    @FXML
    private ScrollPane outputTableScroll;

    private InputTextBox inputField;

    /**
     * Starts the REPL loop and creates the JavaFX window along with other JavaFX controls
     * and event handlers.
     * @param runMethod Function passed in for REPL loop that is called by some event handlers.
     */
    public void initialize(Runnable runMethod) {
        Platform.startup(() -> {
            Stage stage = new Stage();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/Gui.fxml"));
                fxmlLoader.setController(this);
                stage = fxmlLoader.load();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputField = new InputTextBox(textFlow);
            outputTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            printIntro();

            // Event handler for UP and DOWN arrow keys.
            stage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode() == KeyCode.UP) {
                    if (inputField.getAllText().equals("")) {
                        // Can't cycle through command possibilities when
                        // inputField is empty.
                        return;
                    }
                    inputField.appendText("", -1);
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    if (inputField.getAllText().equals("")) {
                        // Can't cycle through command possibilities when
                        // inputField is empty.
                        return;
                    }
                    inputField.appendText("", 1);
                }
            });

            // Event handler for all other keys.
            stage.addEventFilter(KeyEvent.KEY_TYPED, keyEvent ->  {
                switch (keyEvent.getCharacter()) {
                case "\r": // ENTER
                    if (inputField.getAllText().equals("")) {
                        // No input is parsed if there is no text input
                        // in inputField.
                        break;
                    }
                    runMethod.run();
                    break;
                case "\b": // BACK_SPACE
                    inputField.removeFromWord();
                    break;
                case "\t": // TAB
                    if (inputField.getAllText().equals("")) {
                        // Prevents autocompletion when user has not even input anything.
                        return;
                    }
                    inputField.acceptSearchText();
                    keyEvent.consume();
                    break;
                default: // All other characters
                    inputField.appendText(keyEvent.getCharacter(), 0);
                    break;
                }
            });
        });
    }

    /**
     * Reads in user input from inputField and outputs to outputField
     * TextArea.
     * @return Returns String to be used by Parser in REPL loop.
     */
    public String read() {
        String userInput = inputField.getAllText();
        inputField.clearAllText();
        outputField.appendText("\n" + userInput);
        return userInput;
    }

    /**
     * Prints text output in the outputField TextArea.
     * @param printString The raw String to be printed out, after some extra formatting.
     */
    public String print(String printString) {
        String output = printFormatter(printString);
        outputField.appendText("\n" + output);

        return output;
    }

    /**
     * Can be called to redraw the table output as and when needed. Takes in a StockList object
     * that it uses to redraw the entire table.
     * @param stockList Input StockList object to be used to draw the table.
     */
    public void drawTable(StockList stockList) { // TODO: Change to master list...
        ArrayList<ArrayList<String>> tableFormat = stockList.getTableFormat();

        outputTable.getColumns().clear();
        TableColumn mainColumn = new TableColumn(tableFormat.get(0).get(0));
        outputTable.getColumns().add(mainColumn);
        // Iterating through columns to setup all columns.
        for (int i = 1; i < tableFormat.get(1).size(); i++) {
            // Creating column with header
            TableColumn<ArrayList<String>, String> column = new TableColumn<>(tableFormat.get(1).get(i));
            // Assigning column to take row values from data stores in tableFormat ArrayList.
            int finalI = i;
            column.setCellValueFactory(cell -> new ReadOnlyObjectWrapper(cell.getValue().get(finalI)));
            // Adding column to table to be visualized.
            mainColumn.getColumns().add(column);
        }

        // Adding data from tableFormat ArrayList
        for (int i = 2; i < tableFormat.size(); i++) {
            outputTable.getItems().add(tableFormat.get(i));
        }
    }
}
