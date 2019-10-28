package eggventory.ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.ArrayList;

//@@author Raghav-B
public class PredictionTextBox {

    private TextFlow textFlow;
    private Text normalText;
    private Text caretText;
    private Text searchText;

    private InputPredictor inputPredictor;

    public PredictionTextBox(TextFlow textFlow) {
        this.textFlow = textFlow;
        normalText = new Text("");
        caretText = new Text("|");
        caretText.setFill(Color.BLUE);
        searchText = new Text("");
        searchText.setFill(Color.LIGHTGRAY);
        textFlow.getChildren().setAll(normalText, caretText, searchText);

        this.inputPredictor = new InputPredictor();
    }

    /**
     * Appends a character to normalText when a key is pressed.
     * @param appendChar Character to append.
     * @param searchDirection Only used when method is used for cycling through
     *                        command search results.
     */
    public void appendText(String appendChar, int searchDirection) {
        String finalText = normalText.getText() + appendChar;
        String searchResultText = inputPredictor.getPrediction(finalText, searchDirection);
        searchText.setText(searchResultText);
        normalText.setText(finalText);

        textFlow.getChildren().setAll(normalText, caretText, searchText);
    }

    /**
     * Deletes tail character from normalText when backspace is pressed.
     */
    public void removeFromWord() {
        String curText = normalText.getText();

        if (curText.length() - 1 <= 0) {
            inputPredictor.reset();
            normalText.setText("");
            searchText.setText("");

            textFlow.getChildren().setAll(normalText, caretText, searchText);
            return;
        }

        String newText = curText.substring(0, curText.length() - 1);
        String searchResultText = inputPredictor.getPrediction(newText, 0);
        normalText.setText(newText);
        searchText.setText(searchResultText);

        textFlow.getChildren().setAll(normalText, caretText, searchText);
    }

    /**
     * Appends the searchText to the normalText.
     */
    public void acceptSearchText() {
        if (inputPredictor.isCommandFound) {
            return;
        }

        String newText = normalText.getText() + searchText.getText();
        normalText.setText(newText);
        searchText.setText("");

        inputPredictor.reset();
        String searchResultText = inputPredictor.getPrediction(newText, 0);
        searchText.setText(searchResultText);

        textFlow.getChildren().setAll(normalText, caretText, searchText);
    }

    /**
     * Gets all normalText.
     * @return all normalText as a String.
     */
    public String getAllText() {
        return normalText.getText();
    }

    /**
     * Clears both normalText and searchText.
     */
    public void clearAllText() {
        normalText.setText("");
        searchText.setText("");
        textFlow.getChildren().setAll(normalText, caretText, searchText);

        // Resets internal search parameters.
        inputPredictor.reset();
    }
}
