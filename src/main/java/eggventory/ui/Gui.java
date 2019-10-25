//@@author Raghav-B

package eggventory.ui;

import eggventory.StockList;
import eggventory.items.Stock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.IOException;

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
    private TableView outputTable;
    @FXML
    private ScrollPane outputTableScroll;

    private PredictionTextBox inputField;

    public Gui() {
    }

    /**
     * Starts the REPL loop and creates the JavaFX window.
     * @param runMethod Function passed in for REPL loop.
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

            inputField = new PredictionTextBox(textFlow);
            printIntro();

            stage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode() == KeyCode.UP) {
                    System.out.println("UP");
                    // Predictive search code
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    System.out.println("DOWN");
                    // Predictive search code
                } else {
                    // Do nothing!
                }
            });

            // Event handler for pressing keys
            stage.addEventFilter(KeyEvent.KEY_TYPED, keyEvent ->  {
                switch (keyEvent.getCharacter()) {
                    case "\r": // ENTER
                        if (inputField.getAllText().equals("")) {
                            break;
                        }
                        runMethod.run();
                        break;
                    case " ": // SPACE
                        inputField.appendWord();
                        break;
                    case "\b": // BACK_SPACE
                        inputField.removeFromWord();
                        break;
                    case "\t": // TAB
                        // Do prediction search algo here.
                        keyEvent.consume();
                        break;
                    default: // All other characters
                        inputField.appendToWord(keyEvent.getCharacter());
                        break;
                }
            });
        });
    }

    /**
     * Reads in user input from inputField TextBox and outputs to outputField
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
    public void drawTable(StockList stockList) {
        TableColumn<String, Stock> stockTypeCol = new TableColumn<>("Stock Type");
        stockTypeCol.setCellValueFactory(new PropertyValueFactory<>("stockType"));

        TableColumn<String, Stock> stockCodeCol = new TableColumn<>("Stock Code");
        stockCodeCol.setCellValueFactory(new PropertyValueFactory<>("stockCode"));

        TableColumn<Integer, Stock> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<String, Stock> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        /*for (int i = 0; i < stockList.getStockQuantity(); i++) {
            for (int i = 0; i < stockList.getStockType().getQuantity(); i++) {
                outputTable.getItems().add(stockList.getStockType())
            }
        }
        // TODO: Can add more columns for loaned, lost, and minimum later...
        */

        outputTable.getColumns().removeAll();
        outputTable.getColumns().addAll(stockTypeCol, stockCodeCol, quantityCol, descriptionCol);
    }
}
