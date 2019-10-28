package eggventory.ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.ArrayList;

//@@author Raghav-B
public class PredictionTextBox {

    private TextFlow textFlow;
    private Text normalText;
    private Text searchText;

    private InputPredictor inputPredictor;

    public PredictionTextBox(TextFlow textFlow) {
        this.textFlow = textFlow;
        normalText = new Text("");
        searchText = new Text("");
        searchText.setFill(Color.LIGHTGRAY);
        textFlow.getChildren().setAll(normalText, searchText);

        this.inputPredictor = new InputPredictor();
    }

    /**
     * Appends a character to normalText when a key is pressed.
     * @param appendChar Character to append.
     * @param searchDirection Only used when method is used for cycling through
     *                        command search results.
     */
    public void appendText(String appendChar, int searchDirection) {
        String curText = normalText.getText();
        String finalText = curText + appendChar;
        normalText.setText(finalText);

        String searchResultText = inputPredictor.getPrediction(finalText, searchDirection);
        searchText.setText(searchResultText);

        textFlow.getChildren().setAll(normalText, searchText);
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

            textFlow.getChildren().setAll(normalText, searchText);
            return;
        }

        String newText = curText.substring(0, curText.length() - 1);
        normalText.setText(newText);

        String searchResultText = inputPredictor.getPrediction(newText, 0);
        searchText.setText(searchResultText);

        textFlow.getChildren().setAll(normalText, searchText);
    }

    /**
     * Appends the searchText to the normalText.
     */
    public void acceptSearchText() {
        String newText = normalText.getText() + searchText.getText();
        normalText.setText(newText);
        searchText.setText("");

        textFlow.getChildren().setAll(normalText, searchText);
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
        textFlow.getChildren().setAll(normalText, searchText);

        // Resets internal search parameters.
        inputPredictor.reset();
    }
}
