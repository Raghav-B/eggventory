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

    // Triggers when user types a character
    public void appendText(String appendChar, int searchDirection) {
        String curText = normalText.getText();
        String finalText = curText + appendChar;
        normalText.setText(finalText);

        String searchResultText = inputPredictor.getPrediction(finalText, searchDirection);
        searchText.setText(searchResultText);

        textFlow.getChildren().setAll(normalText, searchText);
    }

    // Triggers when user presses backspace
    public void removeFromWord() {
        String curText = normalText.getText();
        String newText = curText.substring(0, curText.length() - 1);
        normalText.setText(newText);

        String searchResultText = inputPredictor.getPrediction(newText, 0);
        searchText.setText(searchResultText);

        textFlow.getChildren().setAll(normalText, searchText);
    }

    public void acceptSearchText() {
        String newText = normalText.getText() + searchText.getText();
        normalText.setText(newText);
        searchText.setText("");

        textFlow.getChildren().setAll(normalText, searchText);
    }

    public String getAllText() {
        return normalText.getText();
    }

    public void clearAllText() {
        normalText.setText("");
        searchText.setText("");
        textFlow.getChildren().setAll(normalText, searchText);
    }
}
