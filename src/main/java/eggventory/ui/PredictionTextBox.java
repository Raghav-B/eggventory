package eggventory.ui;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

public class PredictionTextBox {

    // Uses one Text object per word

    private TextFlow textFlow;
    private ArrayList<Text> textList;
    private int curWordIndex;

    public PredictionTextBox(TextFlow textFlow) {
        this.textFlow = textFlow;
        textList = new ArrayList<>();
        textList.add(new Text(""));
        curWordIndex = 0;
    }

    private void updateTextFlow() {
        textFlow.getChildren().removeAll();
        textFlow.getChildren().addAll(textList);
    }

    // Triggers when user presses the spacebar
    public void appendWord() {
        appendToWord(" ");
        textList.add(new Text(""));
        curWordIndex++;
        updateTextFlow();
    }

    // Triggers when user types a character
    public void appendToWord(String appendChar) {
        String curText = textList.get(curWordIndex).getText();
        textList.get(curWordIndex).setText(curText + appendChar);
        updateTextFlow();
    }

    // Triggers when user presses backspace
    public void removeFromWord() {
        String curText = textList.get(curWordIndex).getText();

        if (curText.length() - 1 <= 0) {
            textList.remove(curWordIndex);
            curWordIndex--;
            return;
        }

        curText = curText.substring(0, curText.length() - 1);
        textList.get(curWordIndex).setText(curText);
        updateTextFlow();
    }

    public String getAllText() {
        StringBuilder sb = new StringBuilder();
        for (Text text : textList) {
            sb.append(text);
        }
        return sb.toString();
    }

    public void clearAllText() {
        textList.clear();
        appendWord();
        updateTextFlow();
    }
}
